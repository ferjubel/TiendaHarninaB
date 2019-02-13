STORE.namespace('STORE.Error');
STORE.namespace('STORE.managementError');
STORE.managementError=this;
'use strict';

STORE.Error.on = function () {
    $("alertaError").style.display = "";
};

STORE.Error.off = function () {
    $("alertaError").style.display = "none";
};

STORE.Error.set_message = function (message) {
    $("alertaError").innerHTML = message;
};

STORE.Error.get_colorError = function () {

    return STORE.Color.colorError;
};