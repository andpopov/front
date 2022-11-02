/*
 * The MIT License (MIT) Copyright (c) 2022 artipie.com
 * https://github.com/artipie/front/LICENSE.txt
 */
package com.artipie.front.rest;

import com.artipie.front.Layout;
import java.util.Optional;
import javax.json.JsonObject;

/**
 * Settings-service.
 *
 * @since 1.0
 */
public class SettingsService extends BaseService {
    /**
     * Path to settings root of rest-api.
     */
    private static final String SETTINGS_PATH = "/api/v1/settings";

    /**
     * Path to layout rest-api.
     */
    private static final String LAYOUT_PATH = String.format("%s/layout", SETTINGS_PATH);

    /**
     * Ctor.
     * @param rest Artipie rest endpoint.
     */
    public SettingsService(final String rest) {
        super(rest);
    }

    /**
     * Obtain list of repository names.
     * @param token Token.
     * @return List of repository names.
     */
    public Layout layout(final String token) {
        return BaseService.handle(
            this.httpGet(Optional.of(token), SettingsService.LAYOUT_PATH),
            res -> {
                final JsonObject json = BaseService.jsonObject(res);
                return Layout.byName(json.getString("layout"));
            }
        );
    }
}
