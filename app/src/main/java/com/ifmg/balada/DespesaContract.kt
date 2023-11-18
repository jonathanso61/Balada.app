import android.provider.BaseColumns

object DespesaContract {
    // Defina a estrutura da tabela
    class DespesaEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "despesas"
            const val _ID = BaseColumns._ID
            const val COLUMN_NOME = "nome"
            const val COLUMN_VALOR = "valor"
            const val COLUMN_DATA = "data"
            const val COLUMN_IS_ENTRADA = "is_entrada"
        }
    }
}
