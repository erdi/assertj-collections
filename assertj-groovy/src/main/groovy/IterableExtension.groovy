import static org.assertj.core.extractor.Extractors.byName

import org.assertj.core.api.iterable.Extractor
import org.assertj.core.groups.FieldsOrPropertiesExtractor
import org.assertj.core.groups.Tuple

class IterableExtension {

    static <F> List<Tuple> extract(Iterable<F> iterable, String... propertiesOrFields) {
        Extractor<?, Tuple> extractor = byName(propertiesOrFields)
        FieldsOrPropertiesExtractor.extract(iterable, extractor)
    }

    static <IN, OUT> List<OUT> extract(Iterable<IN> iterable, Extractor<IN, OUT> extractor) {
        FieldsOrPropertiesExtractor.extract(iterable, extractor)
    }

}
