package fames.systems.bizmanager.infrastructure.utils.values

object Formats {
    val time: (Int) -> String = { "%02d".format(it) }
    val price: (Double) -> String = { "%.2f".format(it).replace('.', ',') }
}