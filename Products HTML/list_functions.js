const addListenerOnDelete=(elements)=>{

    elements.forEach((deleteButton) => {
        deleteButton.addEventListener("click", async (onclick) => {
            onclick.preventDefault();
            let indexOfDeleteRow = onclick.currentTarget.parentNode.parentNode.rowIndex;
            console.log(indexOfDeleteRow);
            let catagoryId = onclick.currentTarget.parentNode.parentNode.querySelector('#id').innerText;
            let deleteURl = await fetch(`http://localhost:8080/products/delete/${catagoryId}`, { method: 'DELETE' });
            let delete_response = deleteURl.ok;
            console.log(delete_response);

            if (delete_response === true) {
                console.log("into the delete block");
                let table = document.querySelector("table");
                table.deleteRow(indexOfDeleteRow);
            }

        })
    })
}

const addListenerOnEdit=(elements)=>{
    elements.forEach((edit) => {

        edit.addEventListener("click", (onclick) => {
            onclick.preventDefault();
            // let catagoryId=onclick.currentTarget.parentNode.parentNode.querySelector('#id').innerText;
            let tableRow = onclick.currentTarget.parentNode.parentNode.querySelectorAll('td');
            console.log(tableRow);
            let values = [];
            tableRow.forEach((data) => {               
                values.push(data.innerText);
            })
            console.log(values);
            let arrayOfCatagoryDropDown=document.querySelector('#catagoryDropDown').querySelectorAll('option');
            console.log(arrayOfCatagoryDropDown);

            let catagoryId;

            arrayOfCatagoryDropDown.forEach((input)=>{
               
               if( input.innerText==values[3]){
                   catagoryId=input.value;
                   return false;                 
               }
                
            });

            console.log(catagoryId);
            
            window.location.href = `updateProduct.html?productId=${values[0]}&name=${values[1]}&description=${values[2]}&catagoryId=${catagoryId}`;
        })
    })
}




const addListenerOnForm=(form)=>{

form.addEventListener("submit", async (event) => {

    event.preventDefault();
    let tableBody = document.getElementById("tableBody");
    console.log(tableBody);


    while (tableBody.firstChild) {
        tableBody.firstChild.remove()
    }

    let formInputValues = Array.from(document.querySelectorAll("form input"));
    let searchString=formInputValues[0].value;
    let sortBy=formInputValues[1].value;
    let pageIndex=formInputValues[2].value;
    let catagoryId = document.getElementById("catagoryDropdown").value;
    let recordSize = document.getElementById("pageSizedropDown").value;
    let sortOrder = document.getElementById("sortingOrderDropDown").value;

let reqJson={
        "catagoryId":catagoryId,
        "search":searchString,
        "sortBy":sortBy,
        "sortOrder":sortOrder,
        "recordsPerPage":recordSize,
        "pageIndex":pageIndex,
  }

    console.log(JSON.stringify(reqJson));

    let response = await fetch("http://localhost:8080/products/_searchProduct", {
        method: 'POST'
        , headers: {
            'Content-Type': 'application/json'
        }
        , body: JSON.stringify(reqJson)
    });


    let json = await response.json();

    let productsArray = [];

    for (var i in json) {
        productsArray.push(json[i]);
    }

 
    console.log(productsArray[0]);
   
    productsArray[0].forEach((product) => {
        console.log(product);
        let tr = document.createElement("tr");
        tr.innerHTML = `
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.productName}</td>
                        <td>${product.productDescription}</td>
                        <td>${product.catagoryName}</td>
                        <td><a href>EDIT</a></td>
                        <td><a href>DELETE</a></td>
                    </tr>
    `
        tableBody.appendChild(tr);
       
    });

    let arrayOfDelete = Array.from(document.querySelectorAll('td a')).filter((a) => a.innerText == "DELETE");   
    let arrayOfEdit = Array.from(document.querySelectorAll('td a')).filter((a) => a.innerText == "EDIT");    
    addListenerOnDelete(arrayOfDelete);
    addListenerOnEdit(arrayOfEdit);

})
}
