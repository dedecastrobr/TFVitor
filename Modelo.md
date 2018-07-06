## Escola Técnica Santo Inácio
### Trabalho Final
### Aluno Vitor Antunes Cazella

## Sumário

> A aplicação desenvolvida foi feita para suprir as nescessidades de uma loja de Aviamentos, era preciso de um sistema de controle de estoque, produtos e clientes.


### Modelo de Dados:

> Descreva as atividades desenvolvidas, apresente os artefatos gerados através de links e textos explicativos.
> 

### Modelo de Classes:

> Para crição do menu da aplicação foi criado com "switch" dando a opção para o usuário acessar o que for necessário.
Como no caso do menu de Clientes onde o usuário tem a opção de inserir, listar, buscar clientes ou até mesmo voltar para o menu anterior.
	switch (op2) {
		case 0:
			inserirCliente();
			break;
		case 1:
		listarClientes();
			break;
		case 2:
			buscaCliente();
			break;
		default:
			break;
		}

Na criação dos menus foram criados ArrayLists com cada opção e para mostrar o menu foi usado um método para arranjar estas opções na tela.
	public class menu {	
		public static List<String> opsMenuCliente = Arrays.asList("Cadastrar Novo Cliente", "Lista de Clientes", "Buscar Cliente");
			public static void main(String[] args) {
				Menu menuC = new Menu("Menu do Cliente", opsMenuCliente);
				menuC.show();
		}
	}

	public void show() {
		System.out.println("\n" + nomeMenu);
		for (String string : opsMenu) {
			System.out.println(opsMenu.indexOf(string) + " - " + string);
		}
		System.out.println("99 - Sair");
	}

### Conclusão:

> A maior complexidade durante a construção do programa era construir os métodos corretos de manipulação do banco de dados, e foi nesta questão que evolui muito, pesquisando, com ajuda dos colegas e do professor. A aplicação acabou sendo bem simples, algo que poderia ter diferente seria esta questão, melhorar a complexidade da aplicação.
