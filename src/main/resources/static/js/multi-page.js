
let arr = document.querySelectorAll('.page');

function switchPage(elemId){
    arr.forEach(element => {
        if (element.id !== elemId){
             element.hidden = true;
        }else{
             element.hidden = false;
        }
    });
}