package todone

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import slinky.core.ExternalComponentNoPropsWithAttributes
import slinky.core.facade.ReactContext

object MaterialIcons {
  trait IconConfiguration extends js.Object {
    val color: js.UndefOr[String] = js.undefined
    val size: js.UndefOr[String] = js.undefined
    val className: js.UndefOr[String] = js.undefined
    val style: js.UndefOr[String] = js.undefined
    val attr: js.UndefOr[String] = js.undefined
    val title: js.UndefOr[String] = js.undefined

    // Builder methods. They aren't part of the Javascript API but we supply
    // them for convenience.
    def withColor(color: String): IconConfiguration
    def withSize(size: String): IconConfiguration
    def withClassName(className: String): IconConfiguration
    def withStyle(style: String): IconConfiguration
    def withAttr(attr: String): IconConfiguration
    def withTitle(title: String): IconConfiguration
  }

  @JSImport("react-icons", "IconContext")
  @js.native
  object IconContext extends ReactContext[IconConfiguration]

  @JSImport("react-icons/md", "MdCheck")
  @js.native
  object MdCheckRaw extends js.Object
  object MdCheck extends ExternalComponentNoProps {
    val component = MdCheckRaw
  }

  @JSImport("react-icons/md", "MdClose")
  @js.native
  object MdCloseRaw extends js.Object
  object MdClose extends ExternalComponentNoProps {
    val component = MdCloseRaw
  }

  @JSImport("react-icons/md", "MdAdd")
  @js.native
  object MdAddRaw extends js.Object
  object MdAdd extends ExternalComponentNoProps {
    val component = MdAddRaw
  }

  @JSImport("react-icons/md", "MdExpandMore")
  @js.native
  object MdExpandMoreRaw extends js.Object
  object MdExpandMore extends ExternalComponentNoProps {
    val component = MdExpandMoreRaw
  }

  @JSImport("react-icons/md", "MdExpandLess")
  @js.native
  object MdExpandLessRaw extends js.Object
  object MdExpandLess extends ExternalComponentNoProps {
    val component = MdExpandLessRaw
  }

  @JSImport("react-icons/md", "MdCheckCircle")
  @js.native
  object MdCheckCircleRaw extends js.Object
  object MdCheckCircle extends ExternalComponentNoProps {
    val component = MdCheckCircleRaw
  }
}
