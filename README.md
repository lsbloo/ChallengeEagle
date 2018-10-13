# ChallengeEagle


  - Para esse desafio, densenvolvi um aplicativo que tem como objetivo, armazenar receitas culinarias.
  - O aplicativo funciona offline usando uma base de dados local(sqlite).
  - Classes que representariam o "crud".
  - UserRepository, RecipeRepository;
  - O aplicativo conta também com o carregamento de imagens a partir do dispositivo do usuário(Galeria).
  - Pacotes:
      * Fragments: contém os "fragmentos" da activity Recipes(Responsavel por salvar e exibir receitas culinarias).
      * models: contem minhas entidades e algumas regras de validaçoes utlizadas no app
      * sqlite: contem classes que representam a persistencia dos dados dessas entidades, como tambem os metodos de armazenamento,editação,busca e remoçao
      * adapter: contem o adaptador do fragmento de visualizacao de receitas, seu objetivo é carregar uma lista de visualização de cada receita de forma customizada
      * notifactions: carrega uma notificao na tela do usuario, quando o mesmo deleta uma receita.
      
