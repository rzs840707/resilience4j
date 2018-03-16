/*
 * Copyright 2017 Bohdan Storozhuk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.resilience4j.ratelimiter.monitoring.endpoint;

import java.util.List;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.monitoring.model.RateLimiterEndpointResponse;

@WebEndpoint(id = "ratelimiter")
public class RateLimiterEndpoint {

    private final RateLimiterRegistry rateLimiterRegistry;

    public RateLimiterEndpoint(RateLimiterRegistry rateLimiterRegistry) {
        this.rateLimiterRegistry = rateLimiterRegistry;
    }

    @ReadOperation
    public RateLimiterEndpointResponse getAllRateLimiters() {
        List<String> names = rateLimiterRegistry.getAllRateLimiters()
                .map(RateLimiter::getName).sorted().toJavaList();
        return new RateLimiterEndpointResponse(names);
    }
}
