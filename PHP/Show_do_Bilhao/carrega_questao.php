<?php
  require("cria_questao.php");
  $questao = load_question(0,"perguntas.json");
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Questões</title>
</head>


<body>

<form>
  <fieldset>
    <p>
      <label><?=$questao->question?></label>
    </p>
    <p>
      <input type="radio" name="questao" id="opcao_1" value="small" />
      <label for="opcao_1"><?=$questao->options[0]?></label>
    </p>
    <p>
      <input type="radio" name="questao" id="opcao_2" value="small" />
      <label for="opcao_2"><?=$questao->options[1]?></label>
    </p>
    <p>
      <input type="radio" name="questao" id="opcao_3" value="small" />
      <label for="opcao_3"><?=$questao->options[2]?></label>
    </p>
    <p>
      <input type="radio" name="questao" id="opcao_4" value="small" />
      <label for="opcao_4"><?=$questao->options[3]?></label>
    </p>
  </fieldset>
</form>


    
</body>
</html>