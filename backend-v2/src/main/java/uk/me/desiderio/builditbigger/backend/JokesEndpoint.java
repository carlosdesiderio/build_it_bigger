/*
 * I have to run and deploy the app invoking Gradle directly:
 *
 * Android Studio doesn't support the new Gradle appengine plugin.
 * The engine development server and deployment support will not work with this new plugin.
 *
 * Uses code and guide from:
 * https://github.com/GoogleCloudPlatform/java-docs-samples/tree/master/appengine-java8/endpoints-v2-backend
 *
 *
 * Command for running appengine locally:
 * {@code
 *      gcloud auth application-default login
 *      gradle appengineRun
 * }
 *
 *
 */

package uk.me.desiderio.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiIssuer;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import uk.me.desiderio.javajokes.Joker;

/**
 * The JokesEndpoint API which Endpoints will be exposing.
 */
@Api(
    name = "jokes",
    version = "v1",
    namespace =
    @ApiNamespace(
        ownerDomain = "backend.builditbigger.desiderio.me.uk",
        ownerName = "backend.builditbigger.desiderio.me.uk",
        packagePath = ""
    ),

    issuers = {
        @ApiIssuer(
            name = "firebase",
            issuer = "https://securetoken.google.com/desjokesapi",
            jwksUri =
                "https://www.googleapis.com/robot/v1/metadata/x509/securetoken@system"
                    + ".gserviceaccount.com"
        )
    }
)


public class JokesEndpoint {

  /**
   * Tells a joke every time is called. The services uses the 'javajokes' library.
   *
   */
  @ApiMethod(name = "tellJoke")
  public Message tellJoke() {
    Joker joker = new Joker();
    String joke = joker.getNextJoke();

    Message message = new Message();
    message.setMessage(joke);
    return message;
  }
}
