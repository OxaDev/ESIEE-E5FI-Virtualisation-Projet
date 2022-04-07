var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");
myHeaders.append('Access-Control-Allow-Origin', '*');

var url_API = "http://back-end-service.default.svc.cluster.local:80/"
url_API = "http://localhost:32000/"
allPeople();

function addPeople() {
    const prenom = document.getElementById("prenom").value;
    const nom = document.getElementById("nom").value;
    const mail = document.getElementById("mail").value;

    var raw = JSON.stringify({
        "id": 4,
        "firstName": prenom,
        "lastName": nom,
        "email": mail
    });

    var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    fetch(url_API+"/AddHuman", requestOptions)
        .then(response => { response.text(); allPeople(); })
        .catch(error => console.log('error', error));

}

function deletePeople(id) {
    var urlDelete = url_API+"Human/" + id.toString();

    var requestOptions = {
        method: 'DELETE',
        redirect: 'follow'
    };

    fetch(urlDelete, requestOptions)
        .then(response => { response.text(); allPeople(); })
        .catch(error => console.log('error', error));

}

function getThisPeople(id) {
    var urlGetThis = url_API+"Human/" + id;
    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    return fetch(urlGetThis, requestOptions)
        .then(response => response.text())
        .catch(error => console.log('error', error));
}

function editPeople(id) {
    getThisPeople(id).then(function (result) {
        var p = JSON.parse(result);
        document.getElementById("idModalEdit").value = p.id;
        document.getElementById("nomEdit").value = p.lastName;
        document.getElementById("prenomEdit").value = p.firstName;
        document.getElementById("mailEdit").value = p.email;
    });
}

function saveEditPeople() {
    var idP = document.getElementById("idModalEdit").value;
    var nomP = document.getElementById("nomEdit").value;
    var prenomP = document.getElementById("prenomEdit").value;
    var mailP = document.getElementById("mailEdit").value;

    var raw = JSON.stringify({
        "id": idP,
        "firstName": prenomP,
        "lastName": nomP,
        "email": mailP
    });

    var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    fetch(url_API+"ModifyHuman", requestOptions)
        .then(response => { response.text(); allPeople(); })
        .catch(error => console.log('error', error));

    $('#editModal').modal('hide');
}

function allPeople() {
    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    fetch(url_API+"AllHumans", requestOptions)
        .then(response => response.text())
        .then(function (result) {
            displayPeople(result);
            // console.log(result);
        })
        .catch(error => console.log('error', error));
}


function displayPeople(allPeople) {
    var tab = document.getElementById("peopleTab");
    var peopleParse = JSON.parse(allPeople);
    tab.innerHTML = "";
    for (let i = 0; i < peopleParse.length; i++) {
        var tr = generateLine(i + 1, peopleParse[i]);
        tab.appendChild(tr);
    }
}

function generateLine(i, people) {
    var prenom = people.firstName;
    var nom = people.lastName;
    var mail = people.email;
    var id = people.id;

    var newTR = document.createElement("tr");

    var newTD0 = document.createElement("td");
    newTD0.appendChild(document.createTextNode(i));
    newTD0.setAttribute('id', id);
    newTR.appendChild(newTD0);

    var newTD1 = document.createElement("td");
    newTD1.appendChild(document.createTextNode(nom));
    newTR.appendChild(newTD1);

    var newTD2 = document.createElement("td");
    newTD2.appendChild(document.createTextNode(prenom));
    newTR.appendChild(newTD2);

    var newTD3 = document.createElement("td");
    newTD3.appendChild(document.createTextNode(mail));
    newTR.appendChild(newTD3);


    var newTD4 = document.createElement("td");
    newTD4.classList.add("text-center");
    newTR.appendChild(newTD4);

    var aEdit = document.createElement("a");
    aEdit.setAttribute("data-toggle", "tooltip");
    newTD4.appendChild(aEdit);

    var bEdit = document.createElement("button");
    bEdit.classList.add("btn");
    bEdit.classList.add("btn-warning");
    bEdit.classList.add("mr-3");
    bEdit.setAttribute("data-toggle", "modal");
    bEdit.setAttribute("data-target", "#editModal");
    bEdit.setAttribute("id", id);
    bEdit.setAttribute("onclick", "editPeople(id)");

    var iEdit = document.createElement("i");
    iEdit.classList.add("fa");
    iEdit.classList.add("fa-pencil");

    bEdit.append(iEdit);
    aEdit.appendChild(bEdit);

    var aDelete = document.createElement("a");
    // aDelete.classList.add("add"); A VOIR A LA FIN
    aDelete.setAttribute("data-toggle", "tooltip");
    newTD4.appendChild(aDelete);


    var bDelete = document.createElement("button");
    bDelete.classList.add("btn");
    bDelete.classList.add("btn-danger");
    bDelete.setAttribute("id", id);
    bDelete.setAttribute("onclick", "deletePeople(id)");

    var iDelete = document.createElement("i");
    iDelete.classList.add("fa");
    iDelete.classList.add("fa-trash");

    bDelete.append(iDelete);
    aDelete.appendChild(bDelete);
    return newTR;
}
