<?php
    class Usuario{
        public $login;
        public $senha;
        public $email;
        public $name;

        public function __construct($login, $senha, $email, $name){
            $this->login = $login;
            $this->senha = $senha;
            $this->email = $email;
            $this->name = $name;
        }    
    }

?>