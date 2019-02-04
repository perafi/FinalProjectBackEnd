package id.co.mandiri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class CategoryWarnaDTO {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CategoriWarnaRequestNewDTO {
        @NotNull
        private String warna;
        private String kode;
        private String description;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CategoryWarnaRequestUpdateDTO {
        @NotNull
        private String id;
        @NotNull
        private String warna;
        private String kode;
        private String description;
    }
}
