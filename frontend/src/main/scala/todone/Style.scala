package todone

import cats.data.NonEmptyChain
import scala.collection.mutable.StringBuilder

/**
 * A little utility to  make it easier to compose CSS styles
 */
final case class Style(style: NonEmptyChain[String]) {
  def +:(s: String): Style =
    Style(s +: style)

  def :+(s: String): Style =
    Style(style :+ s)

  def ++(that: Style): Style =
    Style(this.style ++ that.style)

  override def toString: String = {
    val builder = new StringBuilder(128, style.head)
    style.tail.iterator.foreach{ elt =>
      builder ++= " "
      builder ++= elt
    }

    builder.result()
  }
}
object Style {
  val empty: Style = Style(NonEmptyChain.one(""))

  def one(style: String): Style =
    Style(NonEmptyChain.one(style))

  def apply(style: String, styles: String*): Style =
    Style(NonEmptyChain(style, styles: _*))
}
