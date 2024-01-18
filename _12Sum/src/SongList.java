import java.io.*;

public class SongList {
private int size;
private SongRecord [] list;
private int maxSize;

public SongList() {
    this.size = 0;
    this.maxSize = 0;
    this.list = new SongRecord[maxSize];
}

    public int getSize() {
        return this.size;
    }

    public boolean insert(SongRecord record) {
        // if the size is greater than or equal to maxSize
        if (getSize() >= maxSize) {
            // increase the max size by however many elements are over the maxsize
            increaseSize(size-maxSize+1);

        }

        // if there is space
        if (size < maxSize) {
            size++;  // increase by 1
            list[size - 1] = record;

            return true; // successful
        }

        return false; // not successful
    }


    public boolean delete(SongRecord record) {
        // get the location from the binary search method
        int location = binarySearch(record.getFileName());

        // if the location is found
        if(location >= 0) {
            // delete the list at location
            list[location] = list[size-1];
            size--;

            // sort the records by customer name
            insertionSort();

            return true; // if successful
        }
        return false; // if not successful
    }

    public boolean change(SongRecord oldRecord, SongRecord newRecord) {
        // delete the old record
        if (delete(oldRecord)) {
            insert(newRecord); // insert new record

            // sort the list alphabetically by customer name
            insertionSort();


            return true;
        }
        return false;   // oldR record is not found
    }

    public boolean increaseSize(int sizeToAdd) {
        // if sizeToAdd plus max size is greater than the maxSize
        if(sizeToAdd + maxSize > maxSize) {
            // create a newList with the new size
            SongRecord [] newList = new SongRecord[sizeToAdd+maxSize];
            for(int i = 0; i<size; i++) {
                newList[i] = list[i]; // make the new list equal to the old list
            }

            // equal the new list to the current list
            list = newList;
            maxSize += sizeToAdd; // change the max size to the new size
            return true;
        }

        return false;
    }


    public void insertionSort() {
        // Iterate through the array starting from the second element (index 1)
        for (int top = 1; top < size; top++) {
            // get the current record for comparison
            SongRecord record = list[top];
            int i = top;

            // while i is more than 0 and record to compare is greater than the record before it
            while(i > 0 && record.getFileName().compareToIgnoreCase
                    (list[i-1].getFileName()) < 0) {
                // Shift the elements to make room for the current element
                list[i] = list[i-1];
                i--;
            }

            // Place the current element in its correct sorted position
            list[i] = record;
        }
    }

    public int binarySearch(String searchKey) {
        int low = 0;
        int high = size-1;
        int middle;

        // while the low end is <= high end
        while(low <= high) {
            middle = (high + low)/2;  // divides the array in 2

            // check if the searchKey is found
            if(searchKey.compareToIgnoreCase(list[middle].getFileName()) == 0) {
                return middle;
            }
            // lower end of the alphabet
            else if(searchKey.compareToIgnoreCase(list[middle].getFileName()) < 0) {
                high = middle - 1; // change the high end of the list
            }
            else {
                low = middle + 1; // change the low end of the list
            }
        }

        return -1; // return invalid index if not found
    }

    public void saveToFile(String fileName) throws IOException {
        // set up to write to a new file
        FileWriter fileW = new FileWriter(fileName);
        PrintWriter out = new PrintWriter(fileW);

        // print how many records there are
        out.println(getSize());

        // loop through each of the records
        for(int i = 0; i<size; i++) {
            // create a tempe record
            SongRecord tempRecord = list[i];

            // output the customer info, chequing acc info and savings acc info
            // in a formatted string separated by "/"
            out.println(tempRecord.getFileName() + "/" +
                    tempRecord.getFileSize() + "/" +
                    tempRecord.getDateCreated().toMillis() + "/" +
                    tempRecord.getDateAccessed().toMillis());
        }

        out.close(); // saving the file

    }


    public void readFromFile(String fileToOpen, boolean replace) throws IOException {
        // set up to read from file
        FileReader fileR = new FileReader(fileToOpen);
        BufferedReader in = new BufferedReader(fileR);

        // get the size to add from the first line
        int fileSize = Integer.parseInt(in.readLine());

        // if info needs to be replaced by the info in the file
        if(replace) {
            // set the size from the first line
            size = fileSize;

            // create a new list
            SongRecord [] tempList = new SongRecord[size];

            // get the info and add it to the new list
            for(int i = 0; i<size; i++) {
                // make a temp record from the line in the file
                tempList[i] = new SongRecord(in.readLine());

            }

            list = tempList;	// make the main list equal to the tempList
        }

        // if info needs to be added to the existing info
        else {
            // if adding the elements from the file
            // goes over the maxSize of the list
            if(size+fileSize > maxSize) {
                // increase the maxSize
                maxSize = size + fileSize;

                // create a new list with the max size
                SongRecord [] newList = new SongRecord[maxSize];

                // add the old info to the new list
                for(int i = 0; i<size; i++) {
                    newList[i] = list[i];
                }

                // update the old list with the new increased list
                list = newList;

            }

            // loop from the size to the maximum limit and add the records
            for(int i = size; i<size+fileSize;i++) {
                list[i] = new SongRecord(in.readLine());
            }

            // update the size of the list
            size+=fileSize;

        }

        // close the file
        in.close();

    }





    public static void main(String[] args) {


    }
}
