const inputMatricula = document.querySelector("#matricula");

function gerarMatricula(){
    let txt = "ACA";
    let randomNumber = Math.floor(Math.random() * 15000); // gera um num qualquer

    inputMatricula.value = (txt + randomNumber);
}