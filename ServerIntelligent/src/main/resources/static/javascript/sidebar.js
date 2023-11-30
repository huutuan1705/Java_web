var check1 = true;
function check() {
    if (check1 == true) {
        var a = document.querySelector("#item1");
        a.classList.remove("fa-sort-up");
        a.classList.add("fa-sort-down");
        a.style.paddingTop = "10px";
        a.style.paddingRight = "10px";
        check1 = false;
        var b = document.querySelector("#chose1");
        b.style.display = "none";

    } else {
        var a = document.querySelector("#item1");
        a.classList.add("fa-sort-up");
        a.classList.remove("fa-sort-down");
        a.style.paddingTop = "20px";
        a.style.paddingRight = "10px";
        check1 = true;
        var b = document.querySelector("#chose1");
        b.style.display = "block";
    }
}
var check12 = true;
function check2() {
    if (check12 == true) {
        var a = document.querySelector("#item2");
        a.classList.remove("fa-sort-up");
        a.classList.add("fa-sort-down");
        a.style.paddingTop = "10px";
        a.style.paddingRight = "10px";
        check12 = false;
        var b = document.querySelector("#chose2");
        b.style.display = "none";

    } else {
        var a = document.querySelector("#item2");
        a.classList.add("fa-sort-up");
        a.classList.remove("fa-sort-down");
        a.style.paddingTop = "20px";
        a.style.paddingRight = "10px";
        check12 = true;
        var b = document.querySelector("#chose2");
        b.style.display = "block";
    }
}
var check13 = true;
function check3() {
    if (check13 == true) {
        var a = document.querySelector("#item3");
        a.classList.remove("fa-sort-up");
        a.classList.add("fa-sort-down");
        a.style.paddingTop = "10px";
        a.style.paddingRight = "10px";
        check13 = false;
        var b = document.querySelector("#chose3");
        b.style.display = "none";

    } else {
        var a = document.querySelector("#item3");
        a.classList.add("fa-sort-up");
        a.classList.remove("fa-sort-down");
        a.style.paddingTop = "20px";
        a.style.paddingRight = "10px";
        check13 = true;
        var b = document.querySelector("#chose3");
        b.style.display = "block";
    }
}

var check14 = true;

function check4() {
    if (check14 == true) {
        var a = document.querySelector(".logo");
        a.style.display = "flex";
        var b = document.querySelector(".information");
        var c = document.querySelector(".content");
        c.style.width = "80%";
        b.innerHTML = `
            <div class="information" >
                <div class="login">
                    <i class="fas fa-user-circle fa-lg" style="font-size: 60px; margin-top: 5%; margin-right: 20px;"></i>
                    <div class="checklogin">
                        <i class="fas fa-sort-down icon-down " id="item0"></i>
                        <ul id="header-submenu">
                            <li><a >Information</a></li>
                            <li><a >Out</a></li>
                        </ul>
                    </div>
                    <div class="login-name">
                        <p >Nguyễn Trác Năng</p>
                        <p>Manager system</p>
                    </div>
                </div>
            </div>
            `;
        document.querySelector(".sidebar").style.display = "block";
        check14 = false;
    } else {
        var a = document.querySelector(".logo");
        a.style.display = "none";
        check14 = true;
        var c = document.querySelector(".content");
        c.style.width = "100%";
        var b = document.querySelector(".information");
        b.innerHTML = `
            <div class="information" style ="    justify-content: space-between;">
                <div>
                    <i class="fas fa-bars bar1" onclick="check4()"></i>
                </div>
                <div class="login">
                    <i class="fas fa-user-circle fa-lg" style="font-size: 60px; margin-top: 5%; margin-right: 20px;"></i>
                    <div class="checklogin">
                        <i class="fas fa-sort-down icon-down " id="item0"></i>
                        <ul id="header-submenu">
                            <li><a >Information</a></li>
                            <li><a >Out</a></li>
                        </ul>
                    </div>
                    <div class="login-name">
                        <p >Nguyễn Trác Năng</p>
                        <p>Manager system</p>
                    </div>
                </div>
            </div>
            `; 
        document.querySelector(".sidebar").style.display = "none";
    }
}

