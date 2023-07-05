<?php

$login = $_POST['login'];
$senha = $_POST['senha'];

if($_POST['registrar']){
    registrar();
}

if($_POST['logar']){
    login();
}

if ($_POST['logOut']) {
    unset($_SESSION['Usuarios']);
}

function registrar(){

    $login = $_POST['login'];
    $senha = $_POST['senha'];
    $nome = $_POST['nome'];
    $email = $_POST['email'];
    
    $arquivo = fopen("usuarios.json", "r+");
    if(filesize("usuarios.json") > 0){
        $lerArquivo = json_decode(fread($arquivo, filesize("usuarios.json")));
    }else{
        $lerArquivo = array();
    }
    $usuarioJaExiste = false;
    foreach ($lerArquivo as $usuario) {
        if ($usuario->login == $login) {
            $usuarioJaExiste = true;
            break;
        }
    }

    if(!$usuarioJaExiste){
        array_push($lerArquivo, new Usuario($login, $senha, $email, $nome));
        fseek($arquivo, 0, SEEK_SET);
        fwrite($arquivo, json_encode($lerArquivo));
        fclose($arquivo);
    }else {
        echo "Usuario já existe meu mano";
    }
}

function login(){

    $login = $_POST['login'];
    $senha = $_POST['senha'];
    
    $arquivo = fopen("usuarios.json", "r");
    $lerArquivo = json_decode(fread($arquivo, filesize("usuarios.json")));

    foreach ($lerArquivo as $usuario) {
        if ($usuario->login == $login && $usuario->senha == $senha) {
            $_SESSION['Usuarios'] = $usuario;
            break;
        }
    }

    fclose($arquivo);
}

?>