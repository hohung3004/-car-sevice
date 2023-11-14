package com.notfound.carservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateWishListRequest implements Serializable {

    @NotNull (message = "Không để trống idProduct")
    private Integer idProduct;

    @NotNull (message = "Không để trống idUser")
    private Integer idUser;

    private Integer id;


}
