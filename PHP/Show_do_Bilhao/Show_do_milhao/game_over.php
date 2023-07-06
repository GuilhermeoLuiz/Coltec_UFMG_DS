<?php
session_start();
$saldoAcertos = $_GET['saldoAcertos'];
echo "Game Over!!<br>Saldo de Acertos: " . $saldoAcertos;
?>