package pl.edu.ug.tent.springmvcdemo.validator;

import pl.edu.ug.tent.springmvcdemo.domain.PotatoSack;
import pl.edu.ug.tent.springmvcdemo.result.SingleResult;
import pl.edu.ug.tent.springmvcdemo.utilities.StringUtils;

public class PotatoSackValidator {

    public SingleResult<PotatoSack> validate(PotatoSack potatoSack) {
        if (StringUtils.isNullOrEmpty(potatoSack.getKind())) {
            return new SingleResult<>(
                    "Given kind is not available in this magazine.",
                    false,
                    potatoSack);
        }

        if (potatoSack.getWeight() <= 0.0) {
            return new SingleResult<>(
                    "Sack needs to weight more than 0kg.",
                    false,
                    potatoSack);
        }

        if (potatoSack.getRipDate().after(potatoSack.getExpirationDate())) {
            return new SingleResult<>(
                    "Rip date cannot happen after the expiration date.",
                    false,
                    potatoSack);
        }

        if (StringUtils.isNullOrEmpty(potatoSack.getMagazine())) {
            return new SingleResult<>(
                    "Given magazine doesnt exist.",
                    false,
                    potatoSack);
        }

        return new SingleResult<>("Valid.", true, potatoSack);
    }

}
