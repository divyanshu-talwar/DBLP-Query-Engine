
/**
 *
 * @author Mridul Gupta | Divyanshu Talwar
 */

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Each input string is converted into a set of n-grams, the Jaccard index is
 * then computed as |V1 inter V2| / |V1 union V2|. The input strings are first
 * converted into sets of n-grams (sequences of n characters, also called
 * k-shingles), but this time the cardinality of each n-gram is not taken into
 * account. Distance is computed as 1 - cosine similarity. Jaccard index is a
 * metric distance.
 */
public class Jaccard extends ShingleBased
		implements MetricStringDistance, NormalizedStringDistance, NormalizedStringSimilarity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The strings are first transformed into sets of k-shingles (sequences of k
	 * characters), then Jaccard index is computed as |A inter B| / |A union B|.
	 * The default value of k is 3.
	 *
	 * @param k
	 */
	public Jaccard(final int k) {
		super(k);
	}

	/**
	 * The strings are first transformed into sets of k-shingles (sequences of k
	 * characters), then Jaccard index is computed as |A inter B| / |A union B|.
	 * The default value of k is 3.
	 */
	public Jaccard() {
		super();
	}

	/**
	 * Compute jaccard index: |A inter B| / |A union B|.
	 * 
	 * @param String s11
	 * @param String s22
	 * @return
	 */
	public final double similarity(String s11, String s22) {
		final String s1 = s11.toLowerCase();
		final String s2 = s22.toLowerCase();
		Map<String, Integer> profile1 = getProfile(s1);
		Map<String, Integer> profile2 = getProfile(s2);

		Set<String> union = new HashSet<String>();
		union.addAll(profile1.keySet());
		union.addAll(profile2.keySet());

		int inter = 0;

		for (String key : union) {
			if (profile1.containsKey(key) && profile2.containsKey(key)) {
				inter++;
			}
		}

		return 1.0 * inter / union.size();
	}

	/**
	 * Distance is computed as 1 - similarity.
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public final double distance(final String s1, final String s2) {
		return 1.0 - similarity(s1, s2);
	}
}
