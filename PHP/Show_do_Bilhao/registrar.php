<?php 
    require("Usuario.php"); 

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
        array_push($lerArquivo, new Usuario($nome, $email, $login, $senha));
        fseek($arquivo, 0, SEEK_SET);
        fwrite($arquivo, json_encode($lerArquivo));
        fclose($arquivo);
    }else {
        echo "Usuario já existe.";
    }
?>