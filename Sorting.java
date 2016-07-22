package apcs;

public class Sorting {

	public static void main(String[] args) {
		Window.size(1000, 500);
		Window.setFrameRate(100000);
		Window.frame();

		int[] array = randomArray(Window.width(), 0, Window.height());
		draw(array);
		quickSort(array);
		draw(array);

	}

	public static void quickSort(int[] array) {
		quickSort(array, 0, array.length - 1);
	}

	// start is inclusive, end is exclusive
	public static void quickSort(int[] array, int start, int end) {
		if (start < end) {
			int p = partition(array, start, end);
			quickSort(array, start, p - 1);
			quickSort(array, p + 1, end);
		}
	}

	private static int partition(int[] array, int start, int end) {
		int pivot = array[end];
		int smallIndex = start;
		for (int j = start; j < end; j++) {
			if (array[j] < pivot) {
				swap(array, smallIndex, j);
				draw(array);
				smallIndex++;
			}
		}
		swap(array, smallIndex, end);
		draw(array);
		return smallIndex;
	}

	public static void mergeSort(int[] array) {
		mergeSort(array, 0, array.length);
	}

	// mergeSort, start is inclusive, end is exclusive
	private static void mergeSort(int[] array, int start, int end) {
		if (end - start <= 1) {
			return;
		}
		// divide the array in half, sort the two halves
		int middle = (start + end) / 2;
		mergeSort(array, start, middle);
		mergeSort(array, middle, end);

		// now we have two sorted halves of an array
		// merge step
		int[] sorted = new int[end - start];
		int sortedIndex = 0;
		int leftIndex = start;
		int rightIndex = middle;
		// as long as both halves have elements
		while (leftIndex < middle && rightIndex < end) {

			if (array[leftIndex] < array[rightIndex]) {

				sorted[sortedIndex] = array[leftIndex];
				sortedIndex++;
				leftIndex++;

			} else {

				sorted[sortedIndex] = array[rightIndex];
				sortedIndex++;
				rightIndex++;

			}
		}
		// one of my halves in empty
		if (leftIndex >= middle) {
			while (rightIndex < end) {
				sorted[sortedIndex] = array[rightIndex];
				sortedIndex++;
				rightIndex++;
			}
		} else {
			while (leftIndex < middle) {
				sorted[sortedIndex] = array[leftIndex];
				sortedIndex++;
				leftIndex++;
			}
		}
		// now all the elements are sorted in sorted
		for (int i = 0; i < sorted.length; i++) {
			array[start + i] = sorted[i];
			draw(array);
		}
	}

	public static void insertionSort(int[] array) {
		for (int i = 1; i < array.length; i++) {

			int value = array[i];
			int shiftIndex = i;
			while (shiftIndex > 0 && array[shiftIndex - 1] > value) {
				array[shiftIndex] = array[shiftIndex - 1];
				shiftIndex--;
			}
			array[shiftIndex] = value;
			draw(array);

		}
	}

	public static void bubbleSort(int[] array) {
		boolean swapped = true;
		while (swapped) {
			swapped = false;
			// Go through the array, swapping out of order elements
			for (int i = 0; i < array.length - 1; i++) {
				if (array[i] > array[i + 1]) {
					swap(array, i, i + 1);
					swapped = true;

				}
			}
			draw(array);
		}
	}

	public static void selectionSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int smallestIndex = smallestIndex(array, i);
			swap(array, i, smallestIndex);
			draw(array);
		}
	}

	public static int smallestIndex(int[] array, int start) {
		int smallestIndex = start;
		for (int i = start; i < array.length; i++) {
			if (array[i] < array[smallestIndex]) {
				smallestIndex = i;
			}
		}
		return smallestIndex;
	}

	public static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static int[] randomArray(int length, int min, int max) {
		int[] array = new int[length];
		for (int i = 0; i < array.length; i++) {
			array[i] = Window.random(min, max);
		}
		return array;
	}

	// Draw method using lines. Works best with array length of Window width and
	// max value of Window height
	public static void draw(int[] array) {
		int count = array.length;
		Window.out.background("white");
		int size = Window.width() / count;
		for (int i = 0; i < array.length; i++) {
			int x = i * size + size / 2;
			int y = array[i] / 2;
			Window.out.color(0, 255 * array[i] / Window.height(), 255 - 255
					* array[i] / Window.height());
			Window.out.rectangle(x, Window.height() - y, size, array[i]);
		}

		Window.frame();
	}

	// Draw method using squares. Works best with perfect square array length
	// and max value of 255
	public static void draw(int[] array, boolean grid) {
		int count = (int) Math.sqrt(array.length);

		int size = Window.width() / count;

		Window.out.background("white");
		for (int i = 0; i < array.length; i++) {

			int x = (i % count) * size + size / 2;
			int y = (i / count) * size + size / 2;
			Window.out.color(0, 255 - array[i], array[i]);
			Window.out.square(x, y, size);

		}
		Window.frame();
	}

}
