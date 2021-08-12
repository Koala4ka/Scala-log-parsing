import com.google.inject.{AbstractModule, Singleton}
import dao.ResidentDAO
import dao.impl.ResidentDAOImpl
import javax.inject.Inject
import services.Parser
import play.api.inject._

class Module extends AbstractModule {

  override def configure() = {
    bind(classOf[ResidentDAO]).to(classOf[ResidentDAOImpl])
    bind(classOf[ParserProvider]).asEagerSingleton()

  }
}

@Singleton
class ParserProvider @Inject()(residentDAO:ResidentDAO) {
  new Parser(residentDAO)
}

