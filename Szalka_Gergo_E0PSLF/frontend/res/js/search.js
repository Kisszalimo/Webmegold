var searchBtn = document.getElementById("search-btn");
var searchBar = document.getElementById("search");
var findAll = document.getElementById("showAllBtn");
var findHun = document.getElementById("showHunBtn");
var findEn = document.getElementById("showEnBtn");
var table = document.getElementById("studentTable");

var keyword;
var filterID;
var json;
var obj;
var clearTable = 0;
var err;

searchBtn.addEventListener("click", function () {
getData()
});

findAll.addEventListener("click", function () {
getData(0)
});

findHun.addEventListener("click", function () {
getData(1)
});

findEn.addEventListener("click", function () {
getData(2)
});

function getData(id) {
    keyword = document.getElementById("search").value;
    filterID = document.getElementById("filter").value;
    
    if(id == 0) {
        json = httpGet("http://localhost:8080/students");
    }
    else if(id == 1) {
        json = httpGet("http://localhost:8080/foreign?status=false");
    }
    else if(id == 2) {
        json = httpGet("http://localhost:8080/foreign?status=true");
    }
    else {
        if(filterID == 1) {
            json = httpGet("http://localhost:8080/firstname?fname=" + capitalize(keyword));
        }
        else if(filterID == 2) {
            json = httpGet("http://localhost:8080/lastname?lname=" + capitalize(keyword));        
        }
        else if(filterID == 3) {
            if(Number.isInteger(Number(keyword))) {
                json = httpGet("http://localhost:8080/students/" + keyword);
            }
            else {
                alert("Az ID csak egész szám lehet!");
            }
        }
        else if(filterID == 4) {
            json = httpGet("http://localhost:8080/faculty?fac=" + keyword.toUpperCase());        
        }    
        else if(filterID == 5) {
            json = httpGet("http://localhost:8080/university?uni=" + keyword.toUpperCase());
        }
        else if(filterID == 6) {
            if(Number.isInteger(Number(keyword))) {
                json = httpGet("http://localhost:8080/olderThan?age=" + keyword);
            }
            else {
                alert("Az életkor csak egész szám lehet!");
            }
        }
        else {
            if(Number.isInteger(Number(keyword))) {
                json = httpGet("http://localhost:8080/youngerThan?age=" + keyword); 
            }
            else {
                alert("Az életkor csak egész szám lehet!");
            }            
        }
    }

    convertJson();
    fillTable();
}

function httpGet(theURL) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theURL, false );
    xmlHttp.onloadend = function() {
        if(xmlHttp.status == 404) {
            alert("A keresett ID nem található!");
            err=true;
        }
    };

    try {
    xmlHttp.send();
    }
    catch(e) {
        alert("HIBA: nincs kapcsolat a backenddel.");
    }
    
    return xmlHttp.responseText;
}

function convertJson() {
    obj = JSON.parse(json);
}

function fillTable() {
    
    if(!err) {
        if(clearTable > 0) {
            for(let i = 0; i < clearTable; i++) {
                table.deleteRow(0);
            }
            clearTable = 0;
        }
        
        if(Array.isArray(obj)) {
            var sor0 = table.insertRow(0);
            
            sor0.insertCell(0).outerHTML = "<th>ID</th>";
            sor0.insertCell(1).outerHTML = "<th>Vezetéknév</th>";
            sor0.insertCell(2).outerHTML = "<th>Keresztnév</th>";
            sor0.insertCell(3).outerHTML = "<th>Életkor</th>";
            sor0.insertCell(4).outerHTML = "<th>Egyetem</th>";
            sor0.insertCell(5).outerHTML = "<th>Kar</th>";
            sor0.insertCell(6).outerHTML = "<th>Állampolgárság</th>";
            
            for(let i = 0; i < obj.length; i++) {
                var new_line = table.insertRow(i + 1);
                new_line.insertCell(0).innerHTML = obj[i].id;        
                new_line.insertCell(1).innerHTML = obj[i].lastName;
                new_line.insertCell(2).innerHTML = obj[i].firstName;
                new_line.insertCell(3).innerHTML = obj[i].age;
                new_line.insertCell(4).innerHTML = obj[i].university;
                new_line.insertCell(5).innerHTML = obj[i].faculty;
                new_line.insertCell(6).innerHTML = isForeign(obj[i].foreignStudent);
                
                clearTable++;
            }
            clearTable++;
        }
        else {
            var sor0 = table.insertRow(0);
            var sor1 = table.insertRow(1);
            
            sor0.insertCell(0).outerHTML = "<th>ID</th>";
            sor0.insertCell(1).outerHTML = "<th>Vezetéknév</th>";
            sor0.insertCell(2).outerHTML = "<th>Keresztnév</th>";
            sor0.insertCell(3).outerHTML = "<th>Életkor</th>";
            sor0.insertCell(4).outerHTML = "<th>Egyetem</th>";
            sor0.insertCell(5).outerHTML = "<th>Kar</th>";
            sor0.insertCell(6).outerHTML = "<th>Állampolgárság</th>";
            
            sor1.insertCell(0).innerHTML = obj.id;        
            sor1.insertCell(1).innerHTML = obj.lastName;
            sor1.insertCell(2).innerHTML = obj.firstName;
            sor1.insertCell(3).innerHTML = obj.age;
            sor1.insertCell(4).innerHTML = obj.university;
            sor1.insertCell(5).innerHTML = obj.faculty;
            sor1.insertCell(6).innerHTML = isForeign(obj.foreignStudent);
            
            clearTable = 2;
        }
    }
    else {
        err = false;
    }
}

function isForeign(status) {
    if(status) {
        return "Külföldi";
    }
    else {
        return "Magyar";
    }
}

function capitalize(str) {
    const lower = str.toLowerCase();
    return str.charAt(0).toUpperCase() + lower.slice(1);
}
