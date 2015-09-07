crunchifyAjax();
var intervalId = 0;
intervalId = setInterval(crunchifyAjax, 1000);
var dataArray;
function crunchifyAjax() {
    $.ajax({
        url : '/ajaxDialogs',
        success : function(data) {

            if (dataArray !== data) {
                dataArray = data;
                document.getElementById("body").innerHTML= "<th>Логин</th><th>Сообщение</th>";
                // creates a <table> element and a <tbody> element
                var tbl = document.getElementById("body");
                // creating all cells
                for (var i = 0; i < dataArray.length; i++) {
                    // creates a table row
                    var row = document.createElement("tr");

                    for (var j = 0; j < 2; j++) {
                        // Create a <td> element and a text node, make the text
                        // node the contents of the <td>, and put the <td> at
                        // the end of the table row
                        var cell = document.createElement("td");
                        if (j==0) var cellText = document.createTextNode(dataArray[i].author);
                        else cellText = document.createTextNode(dataArray[i].text);
                        cell.appendChild(cellText);
                        row.appendChild(cell);
                    }
                    // add the row to the end of the table body
                    tbl.appendChild(row);
                }

                // put the <tbody> in the <table>
                // appends <table> into <body>
                // sets the border attribute of tbl to 2;
            }
        }
    });

}
