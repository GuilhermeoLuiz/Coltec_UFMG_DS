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
    
    echo "
    <form method='POST' action='registrar.php'>
    
        <fieldset>

                <p>
                    <label> Nome </label>
                </p>
                    <input type='text' name='nome' id='nome' value=''>
                <p>
                    <label> Email </label>
                </p>
                    <input type='text' name='email' id='email' value=''>
                <p>
                    <label> Login </label>
                </p>
                    <input type='text' name='login' id='login' value=''>
                <p>
                    <label> Senha </label>
                </p>
                <input type='password' name='senha' id='senha' value=''>
                <input type='submit' name='registrar' value='Resgistrar'>

        </fieldset>
    </form>
    ";
}


function login(){

    $eUsuario = false;
    $login = $_POST['login'];
    $senha = $_POST['senha'];
    
    $arquivo = fopen("usuarios.json", "r");
    $lerArquivo = json_decode(fread($arquivo, filesize("usuarios.json")));

    foreach ($lerArquivo as $usuario) {
        if ($usuario->login == $login && $usuario->senha == $senha) {
            $eUsuario = true;
            $_SESSION['Usuarios'] = $usuario;
            break;
        }
    }

    fclose($arquivo);

    if($eUsuario == true){
        echo "
        <form action='perguntas.php' method='GET'>
            <input type='submit' value='Jogar'>
            <input type='hidden' name='id' value='0'>
        </form>
        ";
    }
    else{
        echo "Usuario nao registrado";
    }

    return;
}

?>