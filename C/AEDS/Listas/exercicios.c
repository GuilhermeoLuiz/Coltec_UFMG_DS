#include <stdio.h>
#include <stdlib.h>
#include "listas.h"

#define contante_teste 3
#define SUCESSO 0


int main(int argc, char** argv)
{
	int i, iguais, tam1 = 0, tam2 = 0;
	Lista* l1;
	Lista* l2;
	Lista* temp;
	l1 = lst_cria();
	l2 = lst_cria();


	for (i = 0; i < contante_teste; i++)
	{
		l1 = lst_insere(l1, i);
	}

	for (i = 0; i < 4; i++)
	{
		l2 = lst_insere(l2, i+1);
	}

	for (temp = l1; temp != NULL; temp = temp->prox)
	{
		tam1++;
	}
	for (temp = l2; temp != NULL; temp = temp->prox)
	{
		tam2++;
	}

	if (tam1 == tam2)
	{
		iguais = lst_igual(l1, l2);

		if (iguais == 1)
			printf("Listas iguais");
		else
			printf("Listas diferentes");
	}
	else 
		printf("Listas diferentes");

	free(l1);
	free(l2);

	return SUCESSO;
}