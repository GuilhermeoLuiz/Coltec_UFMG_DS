<?php
// Vetor de enunciados
$enunciados = [
    'Qual o nome do zinchenko?',
    'Qual o nome do prates?',
    'Qual o nome do waltin?',
    'Qual o nome do tm?',
    'Qual o nome do butao?'
];

// Matriz de alternativas
$alternativas = [
    ['a) Artur', 'b) Arthur', 'c) Maciel', 'd) Josué'],
    ['a) Pratis', 'b) Daniel', 'c) Fred+10', 'd) Josué'],
    ['a) Alisson', 'b) Horta', 'c) Fred+10', 'd) Josué'],
    ['a) Tales Mendes', 'b) Thales Morais', 'c) Fred+10', 'd) Josué'],
    ['a) Valter', 'b) Daniel', 'c) Fred+10', 'd) Guilherme']
];

// Vetor de respostas corretas
$respostasCorretas = [1, 1, 0, 1, 3];

// Função para carregar a pergunta com base no ID
function carregaPergunta($id)
{
    global $enunciados, $alternativas, $respostasCorretas;

    if (isset($enunciados[$id])) {
        return [
            'enunciado' => $enunciados[$id],
            'alternativas' => $alternativas[$id],
            'resposta_correta' => $alternativas[$id][$respostasCorretas[$id]]
        ];
    }

    return null;
}
?>