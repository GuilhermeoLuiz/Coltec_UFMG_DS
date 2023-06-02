<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Show do Bilhão - Perguntas</title>
</head>
<body>
    
    <?php include 'menu.inc'; ?>

    <h1>Perguntas do Show do Bilhão</h1>

    <?php
    include 'perguntas.inc';

    // Verifica se foi passado um parâmetro 'id' na URL
    if (isset($_GET['id'])) {
        $perguntaId = $_GET['id'];
        $pergunta = carregaPergunta($perguntaId);

        // Verifica se a pergunta existe
        if ($pergunta !== null) {
            echo '<h2>Pergunta ' . ($perguntaId + 1) . '</h2>';
            echo '<p>' . $pergunta['enunciado'] . '</p>';

            echo '<ul>';
            foreach ($pergunta['alternativas'] as $indice => $alternativa) {
                echo '<li>' . $alternativa . '</li>';
            }
            echo '</ul>';
        } else {
            echo '<p>Pergunta não encontrada.</p>';
        }
    } else {
        echo '<p>Nenhuma pergunta selecionada.</p>';
    }
    ?>
    
</body>
</html>