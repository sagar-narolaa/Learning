window.onload= async()=>{

    let response = await fetch("http://localhost:8080/products/_searchProduct", {method: 'POST' });

    let json = await response.json();
     console.log(json);

    // let obj=JSON.stringify(json);

    // console.log(JSON.parse(obj));

    let productsArray = json.products;

    console.log(productsArray);

    let tableBody = document.getElementById("tableBody");

    productsArray.forEach((product) => {
        console.log(product.name);
        let tr = document.createElement("tr");
        tr.innerHTML = `
                    
                        <td id="id">${product.id}</td>
                        <td>${product.productName}</td>
                        <td>${product.productDescription}</td>
                        <td>${product.catagoryName}</td>                       
                        <td><a href>EDIT</a></td>
                        <td><a href>DELETE</a></td>
                    
    `
        tableBody.appendChild(tr);
    });


    let catagoriesList = await fetch("http://localhost:8080/catagories",{method: 'GET' });

    let catagoriesJson = await catagoriesList.json();
    console.log(catagoriesJson);
    let dropdown = document.querySelector("#catagoryDropdown");
    dropdown.innerHTML += `<option value=0>any</option>`;


    catagoriesJson.forEach((product) => {

        dropdown.innerHTML += `<option value="${product.id}">${product.name}</option>`;

    });

    
    let arrayOfDelete = Array.from(document.querySelectorAll('td a')).filter((a) => a.innerText == "DELETE");   
    addListenerOnDelete(arrayOfDelete);


    let arrayOfEdit = Array.from(document.querySelectorAll('td a')).filter((a) => a.innerText == "EDIT");    
    addListenerOnEdit(arrayOfEdit);

    let form=document.querySelector("form");
    addListenerOnForm(form);

}

















