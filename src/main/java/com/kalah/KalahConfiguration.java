package com.kalah;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class KalahConfiguration extends Configuration {

    @JsonProperty("swagger")
    private SwaggerBundleConfiguration swaggerBundleConfiguration;

    @JsonProperty("board")
    private BoardConfiguration boardConfiguration;

    public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
        return swaggerBundleConfiguration;
    }

    public BoardConfiguration getBoardConfiguration() {
        return boardConfiguration;
    }

    public static class BoardConfiguration {

        public BoardConfiguration() {
            // deserialization
        }

        public BoardConfiguration(int pitsPerPlayer, int stonesPerPit) {
            this.pitsPerPlayer = pitsPerPlayer;
            this.stonesPerPit = stonesPerPit;
        }

        @JsonProperty()
        private int pitsPerPlayer;

        @JsonProperty()
        private int stonesPerPit;

        public int getPitsPerPlayer() {
            return pitsPerPlayer;
        }

        public int getStonesPerPit() {
            return stonesPerPit;
        }
    }

}
