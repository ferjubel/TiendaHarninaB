new STORE.DOMObjectLook("op_updateLoginClient");
new STORE.DOMObjectLook("op_updateDaperClient");
new STORE.DOMObjectLook("op_updateAvatarClient");
new STORE.DOMObjectLook("op_deleteClient");

var ajax = STORE.Ajax;
var llamada;

$("op_updateLoginClient").addEventListener("click", function () {
    $("cuerpo").innerHTML = STORE.clientTemplate.updateLoginClient;
    STORE.clientStrategyOne();
    ponerListenerEnSubmit("/valiCliUpdateLogin", funcionVacia(this));
});


$("op_updateDaperClient").addEventListener("click", function () {
    $("cuerpo").innerHTML = STORE.clientTemplate.updateDaperClient;
    STORE.clientStrategyOne();
    ponerListenerEnSubmit("/updatePersonalData", funcionVacia(this));
});


$("op_updateAvatarClient").addEventListener("click", function () {
    $("cuerpo").innerHTML = STORE.clientTemplate.updateAvatarClient;
    STORE.clientStrategyOne();
    ponerListenerEnSubmit("/UpCliAvaCon", funcionVacia(this));
});


$("op_deleteClient").addEventListener("click", function () {

    confirm("¿Esta usted seguuro de borrar su usuario?")
});

function funcionVacia(any) {
    alert("implementar esto: "+any)
    console.log("implementar esto: "+any)
}

