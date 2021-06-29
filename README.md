# intentstuff

Esse repositório visa ajudar estudantes de desenvolvimento Android fornecendo uma colinha de como enviar diferentes tipos através de `Intent`. 
No exemplo a seguir a `MainActivity` envia dados para a `OtherActivity` por `Intent`.


A `OtherActivity` que receberá os dados por `Intent` se torna responsável sobre inserir os dados, para isso ela definiu um `companion object`

```
    companion object {
        const val EXTRA_STRING_KEY = "EXTRA_STRING_KEY"
        const val EXTRA_BOOLEAN_KEY = "EXTRA_BOOLEAN_KEY"
        const val EXTRA_INT_KEY = "EXTRA_INT_KEY"
        const val EXTRA_LIST_KEY = "EXTRA_LIST_KEY"
        const val EXTRA_FIRST_INDEX_LIST_KEY = "EXTRA_FIRST_INDEX_LIST_KEY"

        fun newIntent(
            context: Context,
            stringValue: String,
            booleanValue: Boolean,
            intValue: Int,
            listValue: List<Song>,
            firstIndexListValue: Song
        ): Intent {
            val intent =  Intent(context, OtherActivity::class.java)

            intent.putExtra(EXTRA_STRING_KEY, stringValue)
            intent.putExtra(EXTRA_BOOLEAN_KEY, booleanValue)
            intent.putExtra(EXTRA_INT_KEY, intValue)
            intent.putParcelableArrayListExtra(EXTRA_LIST_KEY, ArrayList(listValue))
            intent.putExtra(EXTRA_FIRST_INDEX_LIST_KEY, firstIndexListValue)

            return intent
        }
    }
```

Veja que como criamos uma class chamada `Song`, ela precisa ser definida na `Intent` pelo overload da função `putExtra` que recebe um `Parcelable` por parâmetro e no caso da lista usamos a função `putParcelableArrayListExtra`

```
...
            intent.putParcelableArrayListExtra(EXTRA_LIST_KEY, ArrayList(listValue))
            intent.putExtra(EXTRA_FIRST_INDEX_LIST_KEY, firstIndexListValue)
...
```

Para que isso fosse possível, tivemos que fazer a class `Song` extender da class `Parcelable

```
@Parcelize
data class Song(
    val name: String
) : Parcelable
```

A `MainActivity` chama a função `newIntent` para receber uma instância de `Intent` e então navegar para a próxima tela enviando os dados desejados

```
        val strValue = "K-Pop is nice"
        val boolValue = true
        val integerValue = 10
        val songsList = listOf(
                Song("Red Velvet"),
                Song("Day6"),
                Song("Girls' Generation")
        )
        val firstIndexList = songsList[0]

        val otherActivity = OtherActivity.newIntent(
                context = this,
                stringValue = strValue,
                booleanValue = boolValue,
                intValue = integerValue,
                listValue = songsList,
                firstIndexListValue = firstIndexList
        )

        startActivity(otherActivity)
```

Então a `OtherActivity` no onCreate acessa os extras definidos em sua Intent e mostra seus valores

```
        // Get values from intent
        val stringFromIntent = intent.extras?.getString(EXTRA_STRING_KEY)
        val booleanFromIntent = intent.extras?.getBoolean(EXTRA_BOOLEAN_KEY)
        val intFromIntent = intent.extras?.getInt(EXTRA_INT_KEY)
        val listFromIntent = intent.extras?.getParcelableArrayList<Song>(EXTRA_LIST_KEY)?.toList()
        val firstIndexListFromIntent = intent.extras?.getParcelable<Song>(EXTRA_FIRST_INDEX_LIST_KEY)

        // Then show it
        binding.stringValue.text = stringFromIntent
        binding.booleanValue.text = booleanFromIntent.toString()
        binding.intValue.text = intFromIntent.toString()
        binding.listValue.text = listFromIntent.toString()
        binding.firstIndexListValue.text = firstIndexListFromIntent?.name.toString()
```

___

**Faça o clone do projeto e explore o código e faça seus próprios experimentos (⌒‿⌒)**
