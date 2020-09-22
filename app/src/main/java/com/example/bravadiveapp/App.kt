package com.example.bravadiveapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.facebook.stetho.Stetho
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class App : Application() {

    // Estatico para poder llamar a la Db desdecualquier Activity.
    companion object {
        private var db: AppDatabase? = null

        //Funcion para crear el db sino esta creado. Lo comprovamos con el let.
        fun getDatabase(context: Context): AppDatabase {
            db?.let { return it }

            db = Room.databaseBuilder(context, AppDatabase::class.java, "main.db")
                .addCallback(getCallback())
                .build()
            return db as AppDatabase
        }

        //Funcion para cargar los datos al db.
        fun getCallback(): RoomDatabase.Callback {
            return object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {

                    CoroutineScope(Dispatchers.IO).launch {

                        //Creamos todos los spots
                        var spots: List<Spot> = listOf(
                            Spot("Playa de Vallpresona", "Vallpresona una cala aislada, prácticamente virgen.Frente a la playa, en la zona central se alza una roca de notables dimensiones que recibe el nombre de Sa Galera (como tantas otras a lo largo de la costa), cuyos fondos someros son interesantes para los principiantes del buceo en apnea. También son adecuadas para el snorkel las aguas que rodean a la Punta dels Concagats, frente a la que emergen los Esculls dels Concagats, que cierran el extremo de levante de la playa.\n" +
                                    "El paisaje submarino de la Playa de Vallpresona está dominado por una pradera de posidonia que se reparte el espacio con grandes rocas.Entre la posidonia vive una buena representación de la fauna de estos ambientes, sin faltar algunos ejemplares de la nacra mediterránea.El perfil submarino desciende muy lentamente de manera que hay que alejarse mucho para superar los -15 m de profundidad.\n", false, 41.753350f, 2.971817f),
                            Spot("Punta dels Concagats", "I litoral que se extiende desde la Punta dels Concagats hasta la Punta d'en Bosc presenta fondos de cotas moderadas, más adecuados para la apnea. Resultan atractivos los fondos rocosos que rodean a la Punta de Canyet, con profundidades próximas a los 8 m. Los Esculls de Canyet (GPS: 41° 45,440' N; 02° 58,911' E) son arrecifes (unidos por pasarelas artificiales) que se adentran en el mar unos 200 m, separando las playas del Señor Ramón y la de Canyet; esta última ofrece un fondo de arena que alcanza los -5 m de profundidad. Años atrás, en el costado de levante de la Platja de Canyet existió un vivero de langostas. Hay un camino de ronda que bordea la Punta de Canyet y cuenta con un par de glorietas desde las que se obtiene una buena panorámica de la costa.", false, 41.753683f, 2.975700f),
                            Spot("Punta d'en Bosc", "La punta d’en Bosc tiene un aspecto de muralla natural que constituye una buena referencia para la navegación (también resulta inconfundible desde un mirador de la carretera que conduce a Sant Feliu de Guíxols). Este promontorio rocoso marca el extremo occidental de un amplio arco que acaba unos 2,5 km al noreste en la punta de Garbí de Port Salvi, en Sant Feliu de Guíxols. La Punta d'en Bosc, que se adentra en el mar un centenar de metros, se desploma bajo la superficie hasta alcanzar los -14 m, donde da paso a una pendiente que pronto llega a la cota de los -20 m. Hacia mar abierto, el paisaje submarino muestra unas mesetas rocosas que se elevan sobre el fondo de arena y que forman taludes llenos de grietas. Asimismo, en la vertiente de levante de la punta hay un conjunto de piedras\n" +
                                    "situadas a unos -14 m de profundidad que suelen cobijar un gran número de peces, incluidos los sargos, las doradas , los meros, etc.\n", false, 41.765036f,3.0031476f),
                            Spot("Cala del vigatà", "La pequeña Cala del Vigatà se presenta como una estrecha franja de rocas al pie de los acantilados, cubierta de cantos rodados, sin apenas arena, y da paso a unas aguas limpias sobre un fondo que combina rocas y arena a cotas moderadas. La zona, que alcanza los -6m a poca distancia de la playa, es poco visitada por los buceadores con escafandra, pero recibe a muchos practicantes de la apnea y de la pesca submarina. Desde la Cala del Vigatà hasta la Punta de la Cova, la costa se curva hacia el sur, quedando a resguardo de los vientos de levante. Es muy acantilada y acaba en un paisaje dominado por la arena entre los -10 my-15 m.\n", false, 41.753683f, 3.0222648f),
                            Spot("Roca S'adolitx", "El Islote de Sadolig o Roca s'Adolitx (o s'Adolig) se eleva media docena de metros sobre la superficie del mar. Está separado de la costa por un freo de unos 30 m de anchura y unos -10 m de profundidad, de modo que puede navegarse sin muchos problemas por el costado de tierra.\n" +
                                    "El paisaje submarino alrededor del islote resulta interesante. En dirección a mar abierto, comunica con un fondo de arena que pronto supera los -25 mo-27 m. Buena parte de la vertiente de poniente está recubierta por piedras de mediano tamaño que parecen amontonadas por un desprendimiento. En general, junto a la roca se descubren pulpos, langostas, bancos de castañuelas, sargos, mojarras, meros, morenas, congrios y, ocasionalmente, algún pez ballesta; últimamente se ha observado también con cierta frecuencia el vuelo de rayas pastinacas en estas aguas.\n" +
                                    "En la base del islote (en su ladera norte, es decir, en el sector más cercano a la costa), se\n" +
                                    "encuentra la \"Cuarta Cueva\". En realidad se trata de un estrecho túnel submarino con el fondo situado a -13 m de profundidad. Sus dimensiones son bastante reducidas: algo más de 1 m de altura, apenas un par de metros de ancho y unos 5 m de recorrido total, que transcurre con un rumbo de 120°-300°. Si la visibilidad lo permite, es posible admirar el recubrimiento de invertebrados que puebla el techo y a los salmonetes reales que se mueven junto a las paredes. Se trata además de un túnel \"fantasma\", ya que en determinadas épocas se llena de arena hasta casi desaparecer, siendo imposible atravesarlo.\n", false, 41.770833f, 3.028583f),
                            Spot("Cala Rovira", "Cala Rovira está rodeada de escollos y sólo alcanza los 2 m en el centro, con un fondo de 17m  de arena y alguna roca, de modo que sólo es accesible, con el mar en calma, para barcas de poco calado; resulta bastante interesante para la apnea. La playa es amplia, con arena de grano grueso que cae en un talud a los pocos pasos de entrar en el agua; una riera, casi siempre seca, desemboca en la parte posterior. En su extremo de levante, hay un bloque rocoso con algunos pinos, unido a la costa por una lengua de arena conocida como el Xuclador y que comunica con la pequeña Cala de Sa Cova. Frente a la Cala de Sa Cova, media milla mar adentro, hay un arrecife artificial entre los -18 m y-22 m, en dos alineaciones paralelas de unos 300 m, separadas un centenar de metros de distancia.", false, 41.821717f, 3.074117f),
                            Spot("Roca Roja", "Se trata de una imponente cordillera submarina orientada hacia levante (90°).\n" +
                                    "La cordillera tiene varias cumbres entre los -10,5 m y los -14 m, aunque la mayor parte se sitúa alrededor de los -20 m de profundidad.\n" +
                                    "La Roca Roja arranca de un fondo arenoso en el que son frecuentes las tembladeras, los peces planos, etc. En la vertiente norte, la extensión de arena se encuentra entre los -25 m y los -27 m de profundidad, aunque muy cerca se alcanzan los -29 m; en cambio, la vertiente sur se hunde en una llanura de arena (-29 m) salpicada de pequeñas rocas con algún llamativo ejemplar de la esponja Axinella.\n" +
                                    "Una de las zonas más atractivas de la inmersión es el extremo de levante. Alli la pared se desploma casi a pico desde los -10 m hasta los -29 m de profundidad. Junto a la base, apenas a un par de metros de distancia, se alzan varios bloques rocosos de gran tamaño separados entre si por estrechos pasillos de arena; estas piedras se elevan hasta los -26 m de profundidad, creando paredes y extraplomos que proporcionan el ambiente perfecto para una rica representación del coralígeno, destacando las colonias de briozoos, el coral rojo, las langostas y algún bogavante, además de santiaguiños, morenas y congrios.\n", false, 41.817483f, 3.091067f),
                            Spot("La Pintaella", "La Pinatella a Pinatella o Pignatella es un grupo de rocas Las rocas son más o menos aplanadas, con pendientes que forman pequeños escalones y están separadas por pasadizos. Aunque no es un escenario espectacular, entre las grietas y orificios de las formaciones rocosas, que están rodeadas por un entorno de arena, suelen refugiarse numerosos peces. El sector más profundo de la Pinatella también recibe la denominación de Pinatella de Fora o Roca de la Iglesia. Geológicamente es una prolongación de la anterior, aunque se encuentra más hacia mar abierto. Este paisaje submarino está formado por un conjunto de rocas que se alzan sobre un fondo desde los -32 ma-35 m, con una serie de mesetas superiores que alcanzan aproximadamente los -20 ma-28 m. La mitad exterior se achata de modo gradual hasta casi desaparecer, dando paso a un roquero de aspecto plano y aislado, al que se aferra una vieja red de arrastre desde hace mucho tiempo, tal como sucede en otros muchos ambientes submarinos de esta zona.", false, 41.778633f, 3.042333f),
                            Spot("Illes Formigues", "Se trata de un grupo de islotes graníticos, desprovistos de vegetación, que exhiben colores rojizos en la vertiente de garbi y tonos grises en la de levante. Cada islote tiene nombre: la Formiga Gran, la Planassa, la Corva y el Llagostí. El islote más grande (la Formiga Gran) mide poco más de 25 m de longitud y cuenta con un faro automático desde 1982. Las Illes Formigues no son un buen refugio cuando hay oleaje o vientos fuertes. \n" +
                                    "En la vertiente de poniente (o de garbi), se observa una suave pendiente de ambiente fotófilo, cubierta por bloques, sin caídas bruscas ni grutas. Las cotas oscilan aproximadamente entre los -12 m y los -24 m, entre rocas y posidonia. El precoralígeno empieza a cierta profundidad, algo alejado de la parte emergida de los islotes. Abundan los lábridos y también se ve asomar la silueta alguna morena entre las grietas. Hacia mar abierto, el fondo se presenta bastante más accidentado, con numerosas rocas aquí y allá (algunas de las rocas casi afloran a la superficie); en la misma dirección, el fondo submarino se desploma hasta alcanzar los -25 m de profundidad, donde hay una rica comuni- dad de coralígeno. La vertiente de levante Es la más espectacular. Hacia la costa, raramente se superan los -20 m de profundidad, pero hacia mar abierto existe una sucesión de barrancos y grandes bloques escalonados, con cumbres y plataformas sucesivas (-18 ma-32 m); la arena domina a partir de los -42 m. Las algas fotófilas y las posidonias de los primeros metros, envueltas por nubes de castañuelas, dan paso a paredes cubiertas de gorgonias y un ambiente coralígeno en los canales a partir de los -25 m. Fauna más destacable: sargos, pagros, cántaras, rascacios, lábridos, alguna morena, algún rape (en primavera), tres colas, coral rojo (desde los -30 m de profundidad), santiaguiños, sastres, alguna langosta, etc. Al sur del archipiélago se extiende un amplio fondo poco profundo, que desciende con suavidad desde los -12 m hasta los -24 m. El paisaje submarino consiste en rocas aisladas y restos de pradera de posidonia.\n", false, 41.8634939f,3.1852151f),
                            Spot("Les Sofreres", "Justo a continuación de Cala Jonca, se observan unos acantilados de color rojizo con manchas amarillentas que reciben el nombre de Les Sofreres (sofre significa azufre, en catalán). Dada la escasa profundidad y la orientación de la zona, abierta al sur y expuesta durante muchas horas a la influencia de los rayos del sol, el paisaje submarino está dominado por los tonos verdes de las algas fotófilas (amantes de la luz), entre las que se extienden los tentáculos de algunas anémonas, en particular anémonas comunes o fideos de mar (Anemonia sulcata). También la posidonia se extiende hasta más allá de los -20 m de profundidad.  El paisaje submarino no presenta grandes dificultades al buceador, de modo que resulta adecuado para los cursillistas y los principiantes. El fondo consiste en una plataforma que describe una pendiente suave hacia el sur, hacia mar abierto. El lecho submarino está cubierto de arena, guijarros y piedras, que comparten el espacio con matas de posidonia y grandes bloques rocosos, que parecen el resultado de desprendimientos de las paredes exteriores. ", false, 41.7802408f,3.0309431f)
                        )

                        //Añadimos la lista de spots a la DB.
                        App.db?.spotDao()?.insertAll(spots)

                        App.db?.let {
                            spots = it.spotDao().getAll()
                        }

                        //Creamos los SpotImage y los relacionamos con su Spoti id.
                        val imageSpots: List<SpotImage> = listOf(
                            SpotImage(R.mipmap.image_test_1, spots[0].spotId),
                            SpotImage(R.mipmap.image_test_2, spots[0].spotId),
                            SpotImage(R.mipmap.image_test_3, spots[1].spotId),
                            SpotImage(R.mipmap.image_test_4, spots[1].spotId),
                            SpotImage(R.mipmap.image_test_5, spots[2].spotId),
                            SpotImage(R.mipmap.image_test_6, spots[2].spotId),
                            SpotImage(R.mipmap.image_test_7, spots[3].spotId),
                            SpotImage(R.mipmap.image_test_8, spots[3].spotId),
                            SpotImage(R.mipmap.image_test_9, spots[4].spotId),
                            SpotImage(R.mipmap.image_test_10, spots[4].spotId),
                            SpotImage(R.mipmap.image_test_11, spots[5].spotId),
                            SpotImage(R.mipmap.image_test_12, spots[5].spotId),
                            SpotImage(R.mipmap.image_test_13, spots[6].spotId),
                            SpotImage(R.mipmap.image_test_14, spots[6].spotId),
                            SpotImage(R.mipmap.image_test_15, spots[7].spotId),
                            SpotImage(R.mipmap.image_test_16, spots[7].spotId),
                            SpotImage(R.mipmap.image_test_17, spots[8].spotId),
                            SpotImage(R.mipmap.image_test_18, spots[8].spotId),
                            SpotImage(R.mipmap.image_test_19, spots[9].spotId),
                            SpotImage(R.mipmap.image_test_20, spots[9].spotId)
                        )
                        //Añado los SpotImage al DB.
                        App.db?.spotImageDao()?.insertAll(imageSpots)

                        var iconList: List<Icon> = listOf(
                            /*0*/Icon("Precoralígeno",R.drawable.ic_starfish),
                            /*1*/Icon("Fondeo",R.drawable.ic_anchor),
                            /*2*/ Icon("Fondeo Fijo",R.drawable.ic_boya),
                            /*3*/ Icon("Elevado consumo de Oxigeno",R.drawable.ic_consumo_oxigeno),
                            /*4*/ Icon("Corriente frecuente",R.drawable.ic_corriente),
                            /*5*/ Icon("Aconsejado el uso de Linterna",R.drawable.ic_luz_de_buceo),
                            /*6*/ Icon("Parada de descompresión",R.drawable.ic_parada_descompresio),
                            /*7*/ Icon("Acceso por playa",R.drawable.ic_beach),
                            /*8*/ Icon("Fondo de roca",R.drawable.ic_rocas),
                            /*9*/ Icon("Fondo de arena",R.drawable.ic_sand),
                            /*10*/ Icon("Paso frecuente de embarcaciones",R.drawable.ic_embarcaciones_frecuentes),
                            /*11*/ Icon("Vientos desfavorables",R.drawable.ic_vientos_desfavorables),
                            /*12*/ Icon("Profundidad maxima",R.drawable.ic_prof_max),
                            /*13*/ Icon("Pradera de posidonia",R.drawable.ic_posidonia),
                            /*14*/ Icon("Recomendado para Nocturna",R.drawable.ic_nocturna_clean),
                            /*15*/ Icon("Coralígeno",R.drawable.ic_coral),
                        )

                        //Añado los IconList al DB.
                        App.db?.iconDao()?.insertAll(iconList)
                        App.db?.let {
                            iconList = it.iconDao().getAll()
                        }

                        //Creamos los SpotIcons y los relacionamos con su Spot id.
                        val iconSpots: List<SpotIcon> = listOf(
                            SpotIcon(iconList[0].iconId, spots[0].spotId),
                            SpotIcon(iconList[1].iconId, spots[0].spotId),
                            SpotIcon(iconList[2].iconId, spots[0].spotId),
                            SpotIcon(iconList[3].iconId, spots[0].spotId),
                            SpotIcon(iconList[4].iconId, spots[0].spotId),
                            SpotIcon(iconList[5].iconId, spots[0].spotId),
                            SpotIcon(iconList[6].iconId, spots[1].spotId),
                            SpotIcon(iconList[7].iconId, spots[1].spotId),
                            SpotIcon(iconList[8].iconId, spots[1].spotId),
                            SpotIcon(iconList[9].iconId, spots[1].spotId),
                            SpotIcon(iconList[0].iconId, spots[1].spotId),
                            SpotIcon(iconList[10].iconId, spots[1].spotId),
                            SpotIcon(iconList[11].iconId, spots[1].spotId),
                            SpotIcon(iconList[12].iconId, spots[2].spotId),
                            SpotIcon(iconList[13].iconId, spots[2].spotId),
                            SpotIcon(iconList[14].iconId, spots[2].spotId),
                            SpotIcon(iconList[5].iconId, spots[2].spotId),
                            SpotIcon(iconList[1].iconId, spots[2].spotId),
                            SpotIcon(iconList[7].iconId, spots[2].spotId),
                            SpotIcon(iconList[4].iconId, spots[2].spotId),
                            SpotIcon(iconList[14].iconId, spots[3].spotId),
                            SpotIcon(iconList[11].iconId, spots[3].spotId),
                            SpotIcon(iconList[15].iconId, spots[3].spotId),
                            SpotIcon(iconList[5].iconId, spots[3].spotId),
                            SpotIcon(iconList[2].iconId, spots[3].spotId),
                            SpotIcon(iconList[0].iconId, spots[3].spotId),
                            SpotIcon(iconList[7].iconId, spots[4].spotId),
                            SpotIcon(iconList[1].iconId, spots[4].spotId),
                            SpotIcon(iconList[15].iconId, spots[4].spotId),
                            SpotIcon(iconList[6].iconId, spots[4].spotId),
                            SpotIcon(iconList[12].iconId, spots[4].spotId),
                            SpotIcon(iconList[0].iconId, spots[4].spotId),
                            SpotIcon(iconList[12].iconId, spots[5].spotId),
                            SpotIcon(iconList[14].iconId, spots[5].spotId),
                            SpotIcon(iconList[4].iconId, spots[5].spotId),
                            SpotIcon(iconList[15].iconId, spots[5].spotId),
                            SpotIcon(iconList[10].iconId, spots[5].spotId),
                            SpotIcon(iconList[13].iconId, spots[5].spotId)
                        )

                        //Añado los IconSpots al Db.
                        App.db?.spotIconDao()?.insertAll(iconSpots)
                    }
                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                }
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        //Iniciamos Stettho
        Stetho.initializeWithDefaults(this)

        //Creamos la base de datos.
        getDatabase(this)
    }
}
