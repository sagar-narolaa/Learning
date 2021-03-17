let catagoryId;

window.onload = ()=> {
    let url = window.location.href
    console.log(url);
    let params = url.split('?')[1].split('&');
    console.log(params);
    let values=[];  // array of uri values

    params.forEach( (par)=> values.push(par.split('=')[1]));
     console.log(values);

    let valuesWithoutPercentage=[]; 
    values.forEach((value)=>{
         let newString=value.replaceAll("%20"," ") ;
         valuesWithoutPercentage.push(newString);
    })
    catagoryId=valuesWithoutPercentage[3];
    console.log(valuesWithoutPercentage);

     let tableData=document.querySelectorAll('td input');
    console.log(tableData);

    let count=1;
    tableData.forEach((data)=>{
        data.value=valuesWithoutPercentage[count];
        //data.setAttribute("value",valuesWithoutPercentage[count]);
        count++;
    })  


    let updateButton=document.querySelector('#submit');
    addListenerOnUpdateButton(updateButton);

 }