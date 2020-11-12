package todone
package data

/**
 * Match a path element as an Id
 */
object IdVar {
  def unapply(path: String): Option[Id] =
    try {
      Some(Id(path.toInt))
    } catch {
      case _: NumberFormatException => None
    }
}
