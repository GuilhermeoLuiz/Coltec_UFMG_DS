#include "Filas.h"
#include <stdio.h>
#include <stdlib.h>

#define SUCESSO 0



int main(int argc, char** argv)
{
	Fila* point_f;
	point_f = fila_cria_vet();
	char tmp_placa[N], lixo;
	
	int i, op, j, t;
	float n = 0, id;

	do
	{
		scanf(" %d", &op);

		switch (op)
		{
		case 1:
		{
			getchar();
			fgets(tmp_placa, N, stdin);

			fila_insere_l(point_f, tmp_placa);

			printf("Quantidade de carros: %d\n", point_f->n);
			printf("Placas:\n");
			for (i = point_f->ini; i < (point_f->ini + point_f->n) % (N + 1); i++)
			{
				printf("%s\n", point_f->vet[i]);
			}
			break;
		}


		case 2:
		{
			getchar();
			fgets(tmp_placa, N, stdin);

			for (i = point_f->ini; i < (point_f->ini + point_f->n) % (N + 1); i++)
			{
				t = 0;
				for (j = 0; j < (N-1); j++)
				{
					if (point_f->vet[i] == tmp_placa[j])
					{
						t++;
						if (t == (N-1))
						{
							fila_retira_vet(point_f, i);
						}
					}
				}
			}

			printf("Quantidade de carros: %d\n", point_f->n);
			printf("Placas:\n");
			for (i = point_f->ini; i < (point_f->ini + point_f->n) % (N + 1); i++)
			{
				printf("%s\n", point_f->vet[i]);
			}

			break;
		}
		default:
			break;
		}

	}while (op != 0);


	return SUCESSO;
}
