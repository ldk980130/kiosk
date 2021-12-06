(function ($, window, document, undefined) {
    let result = {};

    var defaults = {
        cart: [],
        addtoCartClass: '.sc-add-to-cart',
        cartProductListClass: '.cart-products-list',
        totalCartCountClass: '.total-cart-count',
        totalCartCostClass: '.total-cart-cost',
        showcartID : '#show-cart',
        itemCountClass : '.item-count'
    };

    function Item(name, count, price, temperature, size, content) {
        {[
        this.name = name,
        this.count = count,
        this.price = price,
        this.temperature = temperature,
        this.size = size,
        this.content = content
        ]}
    };

    function myItem(cart) {
        let tempObj = [];
        result["orderItems"] = [];
        for (i = 0; i < cart.length; i++) {
            tempObj = {itemName: cart[i].name, count: cart[i].count, temperature: cart[i].temperature, content: cart[i].content, size: cart[i].size}
            result["orderItems"].push(tempObj)
        }
        return result;
    }

    /*Constructor function*/
    function simpleCart(domEle, options) {
        this.options = $.extend(true, {}, defaults, options);
        this.cart = [];
        this.cart_ele = $(domEle);
        this.init();
    }

    /*plugin functions */
    $.extend(simpleCart.prototype, {
        init: function () {
            this._setupCart();
            this._setEvents();
            this._loadCart();
            this._updateCartDetails();
        },
        _setupCart: function () {
            this.cart_ele.addClass("cart-grid panel panel-defaults");
            this.cart_ele.append("<div class='panel-heading cart-heading'><div class='total-cart-count'>Order</div><div class='spacer'></div><div></div></div>")
            this.cart_ele.append("<div class='panel-body cart-body'><div class='cart-products-list' id='show-cart'><!-- Dynamic Code from Script comes here--></div></div>")
            this.cart_ele.append("<div class='cart-summary-container'>\n\
                                <div class='cart-offer'></div>\n\
                                        <div class='cart-total-amount'>\n\
                                            <div>Total</div>\n\
                                            <div class='spacer'></div>\n\
                                            <div><i class='fa fa-dollar total-cart-cost'>0</i></div>\n\
                                            </div>\n\
                                            <div class='cart-checkout'>\n\
                                            <form action='order=success' method ='post'>\n\
                                                <button type='submit' class='btn btn-primary' id='send-to-server'>주문하기</button>\n\
                                            </form>\n\
                                        </div>\n\
                                 </div>");
        },
        _addProductstoCart: function () {
        },
        _updateCartDetails: function () {
            var mi = this;
            $(this.options.cartProductListClass).html(mi._displayCart());
            $(this.options.totalCartCountClass).html("Order");
            $(this.options.totalCartCostClass).html(mi._totalCartCost());
        },
        _setCartbuttons: function () {

        },
        _setEvents: function () {
            var mi = this;
            $(this.options.addtoCartClass).on("click", function (e) {
                e.preventDefault();
                var name = $(this).attr("data-name");
                var cost = Number($(this).attr("data-price"));
                var select_temp = document.querySelector('.select-temp');
                var temperature = select_temp.options[select_temp.selectedIndex].value;
                var select_size = document.querySelector('.select-size');
                var size = select_size.options[select_size.selectedIndex].value;
                var content = document.querySelector('.require').value;
                mi._addItemToCart(name, cost, 1, temperature, size, content);
                mi._updateCartDetails();
            });

            $(this.options.showcartID).on("change", this.options.itemCountClass, function (e) {
                var ci = this;
        e.preventDefault();
        var count = $(this).val();
        var select_temp = document.querySelector('.select-temp');
        var temperature = select_temp.options[select_temp.selectedIndex].value;
        if (select_temp !== null) {
            select_temp.value = temperature;
        }
        var select_size = document.querySelector('.select-size');
        var size = select_size.options[select_size.selectedIndex].value;
        if (select_size !== null) {
            select_size.value = size;
        }
        var name = $(this).attr("data-name");
        var cost = Number($(this).attr("data-price"));
        var select_temp = document.querySelector('.select-temp');
        var temperature = select_temp.options[select_temp.selectedIndex].value;
        var select_size = document.querySelector('.select-size');
        var size = select_size.options[select_size.selectedIndex].value;
        if (select_size !== null) {
            select_size.value = size;
        }
        var content = document.querySelector('.require').value;
        mi._removeItemfromCart(name, cost, count, temperature, size, content);
        mi._updateCartDetails();
    });

        },
        /* 카트에 아이템 추가 */
        _addItemToCart: function (name, price, count, temperature, size, content) {
            for (var i in this.cart) {
                if (this.cart[i].name === name && this.cart[i].temperature === temperature && this.cart[i].size === size && this.cart[i].content === content) {
                    this.cart[i].count++;
                    this.cart[i].price = price * this.cart[i].count;
                    var select_temp = document.querySelector('.select-temp');
                    var temperature = select_temp.options[select_temp.selectedIndex].value;
                    if (select_temp !== null){
                        select_temp.value = temperature
                    } else {
                        alert('음료의 온도를 선택해주세요')
                    }
                    var select_size = document.querySelector('.select-size');
                    var size = select_size.options[select_size.selectedIndex].value;
                    if (select_size !== null) {
                        select_size.value = size;
                    }
                    var content = document.querySelector('.require').value;
                    this._saveCart();
                    return;
                }
            }
            var item = new Item(name, count, price, temperature, size, content);
            this.cart.push(item);
            this._saveCart();
        },
        _removeItemfromCart: function (name, price, count, temperature, size, content) {
            for (var i in this.cart) {
                if (this.cart[i].name === name) {
                    var singleItemCost = Number(price / this.cart[i].count);
                    this.cart[i].price = singleItemCost * count;
                    if (count == 0) {
                        this.cart.splice(i, 1);
                    }
                    var select_temp = document.querySelector('.select-temp');
                    var temperature = select_temp.options[select_temp.selectedIndex].value;
                    if (select_temp !== null){
                        select_temp.value = temperature
                    } else {
                        alert('음료의 온도를 선택해주세요')
                    }
                    var select_size = document.querySelector('.select-size');
                    var size = select_size.options[select_size.selectedIndex].value;
                    if (select_size !== null) {
                        select_size.value = size;
                    }
                    var content = document.querySelector('.require').value;
                    break;
                }
            }
            this._saveCart();
        },
        _clearCart: function () {
            this.cart = [];
            this._saveCart();
        },
        _totalCartCount: function () {
            return this.cart.length;
        },
        _displayCart: function () {
            var cartArray = this._listCart();
            let result = myItem(cartArray);
            console.log(result)
            var output = "";
            if (cartArray.length <= 0) {
                output = "<p>상품을 추가해주세요</p>";
            }
            for (var i in cartArray) {
                output += "<div class='cart-each-product'>\n\
                       <div class='name'>" + cartArray[i].name + "(" + cartArray[i].temperature + "/" + cartArray[i].size + ")" + "</div>\n\
                       <div class='quantityContainer' id='count-form'>\n\
                            <input type='number' class='quantity form-control item-count' data-name='" + cartArray[i].name + "' data-price='" + cartArray[i].price + "' min='0' value=" + cartArray[i].count + " name='number'>\n\
                       </div>\n\
                       <div class='quantity-am'>" + cartArray[i].price + "원" +"</div>\n\
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
        _saveCart: function () {
            localStorage.setItem("shoppingCart", JSON.stringify(this.cart));
        },

        _loadCart: function () {
            this.cart = JSON.parse(localStorage.getItem("shoppingCart"));
            if (this.cart === null) {
                this.cart = [];
            }
        }
    });

    console.log('result: ', result)

    // 주문 데이터 서버로 전송
    $('#send-to-server').click(function(e) {
        e.preventDefault();
        $.ajax({
            url: 'localhost:8080/',
            // url 추후 확인 부탁드립니다
            data: result,
            type: 'POST'
        }).done(function(resp) {
            alert(resp);
            console.log("전송 완료.")
        })
    })

    $('#reset-order').click(function(e) {
        this.cart = [];
        this._saveCart();
    })

    console.log(this.cart)

    $.fn.simpleCart = function (options) {
        return this.each(function () {
            $.data(this, "simpleCart", new simpleCart(this));
            console.log($(this, "simpleCart"));
        });
    }
    ;
})(jQuery, window, document);