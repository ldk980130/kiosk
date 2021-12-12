(function ($, window, document, undefined) {
    /*데이터가 들어갈 변수를 result로 설정*/
    let result = {};
  
    var defaults = {
      cart: [],
      addtoCartClass: ".sc-add-to-cart",
      cartProductListClass: ".cart-products-list",
      totalCartCountClass: ".total-cart-count",
      totalCartCostClass: ".total-cart-cost",
      showcartID: "#show-cart",
      itemCountClass: ".item-count",
    };

    /*cart에 들어갈 데이터를 Item 함수 안에 저장*/
    function Item(name, count, price, temperature, size, content) {
      {
        [
          (this.name = name),
          (this.count = count),
          (this.price = price),
          (this.temperature = temperature),
          (this.size = size),
          (this.content = content),
        ];
      }
    }

    /*cart 안에 i번째 데이터가 있을 때 tempObj 안의 데이터를 result 안에 저장함*/
    function myItem(cart) {
      let tempObj = [];
      result["orderItems"] = []; /*{"orderItems": } 형태로 저장*/
      for (i = 0; i < cart.length; i++) {
        tempObj = {
          itemName: cart[i].name,
          count: cart[i].count,
          temperature: cart[i].temperature,
          content: cart[i].content,
          size: cart[i].size,
        };
        result["orderItems"].push(tempObj);
      }
      /*session storage 안에 result 데이터 저장*/
      sessionStorage.setItem("result", JSON.stringify(result));
  
      return result;
    }
  
    /*Constructor function*/
    function simpleCart(domEle, options) {
      this.options = $.extend(true, {}, defaults, options);
      this.cart = [];
      this.cart_ele = $(domEle);
      this.init();
    }
  
    /*jquery plugin, index.html 내 우측 카트 부분 */
    $.extend(simpleCart.prototype, {
      init: function () {
        this._setupCart();
        this._setEvents();
        this._loadCart();
        this._updateCartDetails();
      },
      _setupCart: function () {
        this.cart_ele.addClass("cart-grid panel panel-defaults");
        this.cart_ele.append(
          "<div class='panel-heading cart-heading'><div class='spacer'></div><div></div></div>"
        );
        this.cart_ele.append(
          "<div class='panel-body cart-body'><div class='cart-products-list' id='show-cart'><!-- Dynamic Code from Script comes here--></div></div>"
        );
        this.cart_ele.append(
          "<div class='cart-summary-container'>\n\
                                  <div class='cart-offer'></div>\n\
                                          <div class='cart-total-amount'>\n\
                                              <div>Total</div>\n\
                                              <div class='spacer'></div>\n\
                                              <div><i class='fa fa-dollar total-cart-cost'>0</i></div>\n\
                                              </div>\n\
                                              <div class='cart-checkout'>\n\
                                              \n\
                                                  <button class='order-form btn btn-primary' onclick='send_to_server()' type='submit' id='send-to-server'>주문하기</button>\n\
                                              \n\
                                          </div>\n\
                                   </div>"
        );
      },
      _addProductstoCart: function () {},
      /* 카트 내부 데이터 변동사항 보여줌 */
      _updateCartDetails: function () {
        var mi = this;
        $(this.options.cartProductListClass).html(mi._displayCart());
        $(this.options.totalCartCountClass).html("Order");
        $(this.options.totalCartCostClass).html(mi._totalCartCost());
      },
      _setCartbuttons: function () {},
      _setEvents: function () {
        /*Item 함수 내 들어갈 변수들 선언.*/
        var mi = this;
        $(this.options.addtoCartClass).on("click", function (e) {
          e.preventDefault();
          var name = $(this).attr("data-name");
          var cost = Number($(this).attr("data-price"));
          var select_temp = document.querySelector(".select-temp");
          /*temperature, size는 select에서 선택된 option의 value, request는 text로 입력된 value*/
          var temperature = select_temp.options[select_temp.selectedIndex].value;
          var select_size = document.querySelector(".select-size");
          var size = select_size.options[select_size.selectedIndex].value;
          var content = document.querySelector(".require").value;
          mi._addItemToCart(name, cost, 1, temperature, size, content);
          mi._updateCartDetails();
        });
  
        $(this.options.showcartID).on(
          "change",
          this.options.itemCountClass,
          function (e) {
            var ci = this;
            e.preventDefault();
            var count = $(this).val();
            var select_temp = document.querySelector(".select-temp");
            var temperature =
              select_temp.options[select_temp.selectedIndex].value;
            if (select_temp !== null) {
              select_temp.value = temperature;
            }
            var select_size = document.querySelector(".select-size");
            var size = select_size.options[select_size.selectedIndex].value;
            if (select_size !== null) {
              select_size.value = size;
            }
            var name = $(this).attr("data-name");
            var cost = Number($(this).attr("data-price"));
            var select_temp = document.querySelector(".select-temp");
            var temperature =
              select_temp.options[select_temp.selectedIndex].value;
            var select_size = document.querySelector(".select-size");
            var size = select_size.options[select_size.selectedIndex].value;
            if (select_size !== null) {
              select_size.value = size;
            }
            var content = document.querySelector(".require").value;
            mi._removeItemfromCart(name, cost, count, temperature, size, content);
            mi._updateCartDetails();
          }
        );
      },
      /* 카트에 아이템 추가 */
      _addItemToCart: function (name, price, count, temperature, size, content) {
        for (var i in this.cart) {
          if ( /*각 요구사항에 따라 주문에 구분을 두도록 함.*/
            this.cart[i].name === name &&
            this.cart[i].temperature === temperature &&
            this.cart[i].size === size &&
            this.cart[i].content === content
          ) {
            this.cart[i].count++;
            this.cart[i].price = price * this.cart[i].count;
            var select_temp = document.querySelector(".select-temp");
            var temperature =
              select_temp.options[select_temp.selectedIndex].value;
            if (select_temp !== null) {
              select_temp.value = temperature;
            } else {
              alert("음료의 온도를 선택해주세요");
            }
            var select_size = document.querySelector(".select-size");
            var size = select_size.options[select_size.selectedIndex].value;
            if (select_size !== null) {
              select_size.value = size;
            }
            var content = document.querySelector(".require").value;
            this._saveCart();
            return;
          }
        }
        var item = new Item(name, count, price, temperature, size, content);
        this.cart.push(item); /*cart에 item push 후 저장*/
        this._saveCart();
      },
      _removeItemfromCart: function (
        name,
        price,
        count,
        temperature,
        size,
        content
      ) {
        for (var i in this.cart) {
          if (this.cart[i].name === name) {
            var singleItemCost = Number(price / this.cart[i].count);
            this.cart[i].price = singleItemCost * count;
            if (count == 0) {
              this.cart.splice(i, 1);
            }
            var select_temp = document.querySelector(".select-temp");
            var temperature =
              select_temp.options[select_temp.selectedIndex].value;
            if (select_temp !== null) {
              select_temp.value = temperature;
            } else {
              alert("음료의 온도를 선택해주세요");
            }
            var select_size = document.querySelector(".select-size");
            var size = select_size.options[select_size.selectedIndex].value;
            if (select_size !== null) {
              select_size.value = size;
            }
            var content = document.querySelector(".require").value;
            break;
          }
        }
        this._saveCart();
      },
      _clearCart: function () {
        this.cart = [];
        this._saveCart();
      },
      /*카트 내 총 상품의 개수 출력: cart 배열의 길이로 return받음*/
      _totalCartCount: function () {
        return this.cart.length;
      },
      _displayCart: function () {
        var cartArray = this._listCart();
        let result = myItem(cartArray);
        console.log(result);
        var output = "";
        if (cartArray.length <= 0) { /*추가한 상품이 없을 때 output 안의 문구가 나옴.*/ 
          output = "<p>상품을 추가해주세요</p>";
        }
        for (var i in cartArray) {
          output +=
            "<div class='cart-each-product'>\n\
                         <div class='name'>" +
            cartArray[i].name +
            "(" +
            cartArray[i].temperature +
            "/" +
            cartArray[i].size +
            ")" +
            "</div>\n\
                         <div class='quantityContainer' id='count-form'>\n\
                              <input type='number' class='quantity form-control item-count' data-name='" +
            cartArray[i].name +
            "' data-price='" +
            cartArray[i].price +
            "' min='0' value=" +
            cartArray[i].count +
            " name='number'>\n\
                         </div>\n\
                         <div class='quantity-am'>" +
            cartArray[i].price +
            "원" +
            "</div>\n\
                         </div>";
        }
        return output;
      },
      _totalCartCost: function () {
        var totalCost = 0;
        for (var i in this.cart) {
          totalCost += this.cart[i].price;
        }
        return totalCost;
      },
      _listCart: function () {
        var cartCopy = [];
        for (var i in this.cart) {
          var item = this.cart[i];
          var itemCopy = {};
          for (var p in item) {
            itemCopy[p] = item[p];
          }
          cartCopy.push(itemCopy);
        }
        return cartCopy;
      },
      _calGST: function () {
        var GSTPercent = 18;
        var totalcost = this.totalCartCost();
        var calGST = Number((totalcost * GSTPercent) / 100);
        return calGST;
      },
      _saveCart: function () { /*로컬스토리지에 cart의 데이터 저장*/ 
        localStorage.setItem("shoppingCart", JSON.stringify(this.cart));
      },
  
      _loadCart: function () { /*로컬스토리지 속 cart의 데이터를 가져옴*/ 
        this.cart = JSON.parse(localStorage.getItem("shoppingCart"));
        if (this.cart === null) {
          this.cart = [];
        }
      },
    });
  
    // result 안의 데이터 출력
    console.log("result: ", result);
  
    $("#reset-order").click(function (e) {
      this.cart = [];
      this._saveCart();
    });
  
    // console.log(this.cart)
  
    $.fn.simpleCart = function (options) {
      return this.each(function () {
        $.data(this, "simpleCart", new simpleCart(this));
        console.log($(this, "simpleCart"));
      });
    };
  })(jQuery, window, document);
  
  /*서버로 result 속 데이터를 보냄*/
  function send_to_server() {
    console.log("result!: ", sessionStorage.getItem("result"));
    $.ajax({
      url: "http://localhost:8080/index",
      /*json 형태로 전송*/
      contentType: "application/json; charset=utf-8",
      /*result 데이터를 session storage 안에 다시 저장*/
      data: sessionStorage.getItem("result"),
      processData: true,
      type: "post",
      success: (data) => {
        alert("주문이 완료되었습니다.");
        window.location.href = './orders';
      },
      error: (err) => {
        alert("주문에 실패했습니다.");
      },
    }).done(function (resp) {
      alert(resp);
      console.log("전송 완료.");
    });
  }
