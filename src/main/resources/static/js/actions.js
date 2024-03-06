"use strict"

const availableCategoryList = document.getElementById("available-category-list");
const selectedCategoryList = document.getElementById("selected-category-list");

removeDuplicates();




function removeDuplicates(){
    for(let selectedCategory of selectedCategoryList){
        for (let i = availableCategoryList.options.length -1; i > -1; i--){
            let availableCategory = availableCategoryList.options[i];
            if(availableCategory.value === selectedCategory.value){
                availableCategoryList.removeChild(availableCategory)
            }
        }
    }
}

function addAtributes(){
    let selectedOptions = availableCategoryList.selectedOptions;
    for (let i = selectedOptions.length -1; i > -1; i--){
        selectedCategoryList.appendChild(selectedOptions[i])
    }
}

function removeAtributes(){
    let selectedOptions = selectedCategoryList.selectedOptions;
    for (let i = selectedOptions.length -1; i > -1; i--){
        availableCategoryList.appendChild(selectedOptions[i])
    }
    selectAll(selectedCategoryList);
}

function selectAll(objSelect){
    [].forEach.call(objSelect.options, (option) => {
        option.selected = true;
    })
}

