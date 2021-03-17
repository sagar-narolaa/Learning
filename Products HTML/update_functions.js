
const addListenerOnUpdateButton=(updateButton)=>{

updateButton.addEventListener("click",async (onclick)=>{

    onclick.preventDefault();

    let tableData=document.querySelectorAll('td input');
    
    console.log(tableData);
  
    let updateJSONArray=[];
    updateJSONArray.push(valuesWithoutPercentage[0]);
    tableData.forEach((data)=>{
        //console.log(data.getAttribute("value"));
        updateJSONArray.push(data.value)
    })

    let updateJSON={
        "id":updateJSONArray[0],
        "name":updateJSONArray[1],
        "description":updateJSONArray[2],
        "catagory":{
            "id":catagoryId
        }       
    }

    console.log(updateJSON);

    console.log( JSON.stringify( updateJSONArray));

    let updateResponse= await fetch("http://localhost:8080/products/update",
    {
    method:'PUT',
    headers:{        
            'Content-Type': 'application/json'
            },        
    body:JSON.stringify(updateJSON)
    }
    );
    
    
    window.location.href=`listProducts.html`;
})

}