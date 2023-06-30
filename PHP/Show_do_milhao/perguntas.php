<?php
require("cria_questao.php");

$id = isset($_GET["id"]) ? (int)$_GET["id"] : 0;
$questao = carregaPergunta($id, "perguntas.inc");

$respostaCorreta = isset($_GET["respostaCorreta"]) ? (int)$_GET["respostaCorreta"] : null;
$respostaInserida = isset($_GET["questao"]) ? (int)$_GET["questao"] : null;

if ($respostaInserida !== null && $respostaInserida !== $respostaCorreta) {
    echo "Resposta errada";
    return;
}

if ($questao->question === null) {
    echo "Fim";
    return;
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Questões</title>
</head>
<body>
    <form action="perguntas.php" method="GET">
        <fieldset>
            <p>
                <label><?= $questao->question ?></label>
            </p>
            <?php foreach ($questao->options as $index => $option) : ?>
                <p>
                    <input type="radio" name="questao" id="opcao_<?= $index ?>" value="<?= $index ?>">
                    <label for="opcao_<?= $index ?>"><?= $option ?></label>
                </p>
            <?php endforeach; ?>
            <input type="hidden" name="respostaCorreta" id="respostaCorreta" value="<?= $questao->answer ?>">
            <input type="hidden" name="id
