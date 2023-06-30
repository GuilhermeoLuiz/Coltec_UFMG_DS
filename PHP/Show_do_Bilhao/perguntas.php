<?php
  require("cria_questao.php");

   $id = htmlspecialchars($_GET["id"]);
  // $questao = carregaPergunta($id, "perguntas.json");
  $questao = carregaPergunta($id, "perguntas.json");

  $respostaCorreta = htmlspecialchars($_POST["respostaCorreta"]);
  $respostaInserida = htmlspecialchars($_POST["questao"]);

  if(trim($respostaInserida) != trim($respostaCorreta))
  {
    echo "Resposta errada";
    return;
  }

  if($questao->question == null)
  {
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
  <form action="perguntas.php?id=<?= $id + 1 ?>" method="POST">
    <fieldset>
      <p>
        <label><?=$questao->question?></label>
      </p>
      <p>
        <input type="radio" name="questao" id="opcao_1" value="0">
        <label for="opcao_1"><?=$questao->options[0]?></label>
      </p>
      <p>
        <input type="radio" name="questao" id="opcao_2" value="1">
        <label for="opcao_2"><?=$questao->options[1]?></label>
      </p>
      <p>
        <input type="radio" name="questao" id="opcao_3" value="2">
        <label for="opcao_3"><?=$questao->options[2]?></label>
      </p>
      <p>
        <input type="radio" name="questao" id="opcao_4" value="3">
        <label for="opcao_4"><?=$questao->options[3]?></label>
      </p>
        <input type="hidden" name="respostaCorreta" id="respostaCorreta" value=<?=$questao->answer?>>
        <!-- <input type="hidden" name="id" id="id" value=<?=$id+1?>> -->
        <input type="submit" name="Enviar" id="enviar" value="enviar">
    </fieldset>
  </form>


    
</body>
</html>