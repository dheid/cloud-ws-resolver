package org.identifiers.cloud.ws.resolver.controllers;

import com.sun.javafx.binding.StringFormatter;
import org.identifiers.cloud.ws.resolver.models.ResolverApiModel;
import org.identifiers.cloud.ws.resolver.models.ResolverApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * Project: resolver
 * Package: org.identifiers.org.cloud.ws.resolver.controllers
 * Timestamp: 2018-01-15 12:31
 * ---
 */
@RestController
public class ResolverApiController {

    @Autowired
    private ResolverApiModel resolverApiModel;

    @RequestMapping(value = "{compactId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> queryByCompactId(@PathVariable("compactId") String compactId) {
        // TODO
        ResolverApiResponse result = resolverApiModel.resolveCompactId(compactId);
        return new ResponseEntity<>(result, result.getHttpStatus());
    }

    @RequestMapping(value = "{selector}/{compactId}", method = RequestMethod.GET)
    public @ResponseBody String queryBySelectorAndCompactId(@PathVariable("selector") String selector, @PathVariable("compactId") String compactId) {
        // TODO
        return StringFormatter.format("[EXTENDED QUERY] With selector '%s' and compact ID '%s'", selector, compactId).getValue();
    }
}
