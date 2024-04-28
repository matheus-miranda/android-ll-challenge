## Delivery App
Modularized app, based on MVI and Clean Architecture. It allows you to create, read, update and delete a delivery form on a local database.
The city field is fetched from a remote API (Brazilian cities) after choosing the State. Static code analysis with detekt, Github Actions CI and complete unit and instrumented testing.

Built with:
- Jetpack Compose
- Hilt
- Room
- ViewModel
- Flow / Coroutines
- Compose Navigation
- Retrofit / OkHttp / Moshi
- Material3
- Mockk
- Resource localization
- Detekt

<img width="720" alt="Screenshot" src="https://github.com/matheus-miranda/android-ll-challenge/assets/15269393/261a06d4-6a7e-4d2e-910c-b07cdbe82053">

### :app
Contains the root NavHost for navigating between features.
All feature modules have their own internal navigation graph implemented with a public NavGraphBuilder extension function.

### :feature
Feature modules contain logically related user flows. Features can navigate between each other through the :app module NavHost.
Features are structured with domain/data/presentation logic for separation of concerns.

### :core:database
Contains database initialization. Features can implement their own tables and expose DAOs to be injected in feature module data sources.

### :core:designsystem
App-wide reusable components and app theme.

### :core:network
Abstracts away the logic for building Retrofit, interceptors, logging etc. Features can just call `retrofit.create()` with their own interface.

### :core:ui
Contains some useful UI utilities.

### :core:testing
Useful testing libraries and helper classes.

### Screenshots
| | | | | | 
| - | - | - | - | - |
| ![Screenshot_1713805325](https://github.com/matheus-miranda/android-ll-challenge/assets/15269393/fa721b8e-b66f-4d11-8731-3ad71d635d4a) | ![Screenshot_1713805403](https://github.com/matheus-miranda/android-ll-challenge/assets/15269393/85b84ed3-4183-4888-acd1-d93773e3cf52) | ![Screenshot_1713805348](https://github.com/matheus-miranda/android-ll-challenge/assets/15269393/87c8f754-e3a8-419f-a2e4-806951149ba3) | ![Screenshot_1713805421](https://github.com/matheus-miranda/android-ll-challenge/assets/15269393/00b6152a-533a-469d-bc75-4e46ffff01da) | ![Screenshot_1713805431](https://github.com/matheus-miranda/android-ll-challenge/assets/15269393/1b378ad1-4f44-4bb5-9dd8-7db060fd7223) |

### Installation instructions
Just run the app module

## Português
Aplicativo modularizado, baseado em MVI e arquitetura limpa. Permite realizar um cadastro de entrega e salvar em banco de dados local.
A lista de cidades é populado a partir de uma consulta na API do IBGE (https://servicodados.ibge.gov.br/api/docs/localidades#api-Municipios-estadosUFMunicipiosGet), após preencher o campo Estado.
Análise estática de código com o detekt, CI com Github Actions e testes unitários e instrumentados.

Construído com:
- Jetpack Compose
- Android Architecture Components
- Hilt
- Room
- ViewModel
- Flow / Coroutines
- Compose Navigation
- Retrofit / OkHttp / Moshi
- Material3
- Mockk
- Localização
- Detekt

### :app
Contém o NavHost raiz para navegar entre as features.
Todos os módulos de feature possuem seu próprio gráfico de navegação interna implementado com uma função de extensão pública do NavGraphBuilder.

### :feature
Os módulos de feature contêm fluxos logicamente relacionados. Features podem navegar entre si por meio do NavHost do módulo :app.
As features são estruturadas com lógica de domínio/dados/apresentação para separação de camadas.

### :core:database
Contém inicialização do banco de dados. As features podem implementar suas próprias tabelas e expor DAOs para serem injetados em seus data sources. 

### :core:designsystem
Componentes reutilizáveis em todo o aplicativo e o tema base.

### :core:network
Abstrai a lógica para construção do Retrofit, interceptores, logging etc. As features podem simplesmente chamar o `retrofit.create()` com sua própria interface.

### :core:ui
Contém alguns utilitários de úteis de UI.

### :core:testing
Bibliotecas de testes e classes auxiliares.

### Instruções de instalação
Apenas executar o módulo :app
