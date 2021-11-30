const addNewManufacturerBtn = document.getElementById("manufacturers-add-new-btn");
const addNewModelBtn = document.getElementById("model-add-new-btn");

const modelListsContainer = document.querySelector(".model-container-list");
const manufacturersList = document.getElementById("manufacturers-list");

const manufacturersInput = document.getElementById("manufacturers-input");
const modelInput = document.getElementById("model-input");

let selectedManufacturer;

function createItem(input, list) {
    let listItem = document.createElement("li");
    let inputValue = input.value;
    listItem.appendChild(document.createTextNode(inputValue.toUpperCase())); 
    list.appendChild(listItem);
    return listItem;
}

function createModelsList(manufacturerName) {
    let newModelList = document.createElement("ul");
    newModelList.setAttribute("id", manufacturerName + "-list");
    modelListsContainer.insertBefore(newModelList, modelListsContainer.firstChild);
}

const listItemCreator = {
    manufacturersAdd() {
        const listItem = createItem(manufacturersInput, manufacturersList);
        listItem.addEventListener("click", selectManufacturer);

        createModelsList(manufacturersInput.value.toLowerCase());
        
        hideInputField(manufacturersInput);
    },
    modelAdd() {
        let modelsList = document.getElementById(selectedManufacturer + "-list");
        createItem(modelInput, modelsList);

        modelInput.value = "";
    }
}

function showInputField(event) {
    const blockContainer = event.target.parentElement.parentElement;
    let formContainer = blockContainer.querySelector(".input-container");
    formContainer.style.display = "flex";

    let submitBtn = blockContainer.querySelector(".submit-btn");
    submitBtn.addEventListener("click", listItemCreator[blockContainer.id + "Add"]);    

    event.target.style.display = "none"
}

function hideInputField(input) {
    const inputContainer = input.parentElement;
    inputContainer.style.display = "none";

    const addNewBtn = inputContainer.parentElement.querySelector(".add-new-btn");
    addNewBtn.style.display = "block";

    input.value = "";
}

function changeColor(list) {
    list.forEach(x => x.style.color = "rgb(66, 63, 63)");
}

function hideLists(list) {
    list.forEach(x => x.style.display = "none");
}

function selectManufacturer(event) {
    changeColor(Array.from(manufacturersList.querySelectorAll("li")));
    hideLists(Array.from(modelListsContainer.querySelectorAll("ul")));
    hideInputField(modelInput);
    
    selectedManufacturer = event.target.textContent.toLowerCase();
    event.target.style.color = "#eaae00";
    let modelList = document.getElementById(selectedManufacturer + "-list");
    modelList.style.display = "block";

    addNewModelBtn.addEventListener("click", showInputField);
    addNewModelBtn.style.display = "block"
}

function addInitialValues() {
    manufacturers = ["Ford", "Bmw"];
    models = {
        Ford: ["Fiesta", "Focus"],
        Bmw: ["M50", "M135i"]
    }

    manufacturers.map(name => {
        manufacturersInput.value = name;
        listItemCreator.manufacturersAdd();
        selectedManufacturer = name.toLowerCase();
        models[name].map(model => {
            modelInput.value = model;
            listItemCreator.modelAdd();
        })
    })

    let allModelsLists = Array.from(document.querySelectorAll(".model-container-list ul"));
    allModelsLists.map(x => x.style.display = "none");
    addNewModelBtn.style.display = "none"

}

addNewManufacturerBtn.addEventListener("click", showInputField);
addInitialValues();