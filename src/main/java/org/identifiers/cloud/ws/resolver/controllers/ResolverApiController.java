package org.identifiers.cloud.ws.resolver.controllers;

import org.identifiers.cloud.ws.resolver.models.ResolverApiModel;
import org.identifiers.cloud.ws.resolver.models.api.responses.ServiceResponse;
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
    // TODO - Prepare the ground for the endpoints implementing the new use cases
    // TODO - Take this opportunity to refactor the health checks into its own controller

    @Autowired
    private ResolverApiModel resolverApiModel;

    @RequestMapping(value = "{compactId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> queryByCompactId(@PathVariable("compactId") String compactId) {
        // NOTE - I don't like how this looks, if handling exceptions at controller level I think I should go for
        // @ControllerAdvice, but it depends on where the exception belongs to, I think all exceptions belonging to the
        // business logic should be caught and handled at the model level (the main model associated to the controller),
        // and only request related exceptions should be handled at the controller level, probably via @ControllerAdvice
        // mechanism and error controller, that I need to implement anyway.
        ServiceResponse result = resolverApiModel.resolveCompactId(compactId);
        return new ResponseEntity<>(result, result.getHttpStatus());
    }

    @RequestMapping(value = "{selector}/{compactId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> queryBySelectorAndCompactId(@PathVariable("selector") String selector, @PathVariable("compactId") String compactId) {
        // NOTE - I don't like how this looks, if handling exceptions at controller level I think I should go for
        // @ControllerAdvice, but it depends on where the exception belongs to, I think all exceptions belonging to the
        // business logic should be caught and handled at the model level (the main model associated to the controller),
        // and only request related exceptions should be handled at the controller level, probably via @ControllerAdvice
        // mechanism and error controller, that I need to implement anyway.
        ServiceResponse result = resolverApiModel.resolveCompactId(compactId, selector);
        return new ResponseEntity<>(result, result.getHttpStatus());
    }

    // liveness probe
    @RequestMapping(value = "/liveness_check")
    public String livenessCheck() {
        // TODO - This will be refactored out later, it will be the model who will implement the logic to determine
        // TODO - whether the service should be considered "alive" or not, but this code will live here for testing
        // TODO - purposes
        return resolverApiModel.livenessCheck();
    }

    // Readiness check
    @RequestMapping(value = "/readiness_check")
    public String readinessCheck() {
        // TODO - This will be refactored out later, it will be the model who will implement the logic to determine
        // TODO - whether the service should be considered "ready" or not, but this code will live here for testing
        // TODO - purposes
        return resolverApiModel.readinessCheck();
    }

}
