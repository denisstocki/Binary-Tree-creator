#include <iostream>
#include "BinaryTree.hpp"

using namespace std;

int main(){

    string text;
    int a, nowy;
    double b;
    string c;




        string tree_type;
            bool tree_picked = false;
            bool close = false;
        

            /**
             * DRZEWA BINARNE POSZCZEGOLNYCH TYPOW
             */
            BinaryTree<int> integerBinaryTree;
        BinaryTree<double> doubleBinaryTree;
          BinaryTree<string> stringBinaryTree;
        

    
            
            /**
             * WYSYLANIE DO SOCKETA
             */
            cout<<"Welcome to BST creator !\n";
            cout<<"Type one of the following tree types:\n";
            cout<<"1: '1' - int\n";
            cout<<"2: '2' - double\n";
            cout<<"3: '3' - string\n";

            /**
             * PETLA DZIALAJACA DOPOKI UZYTKOWNIK NIE WYBIERZE RODZAJU DRZEWA
             */
            while (!tree_picked){

                /**
                 * SWITCH WYBIERAJACY RODZAJ DRZEWA
                 */
                cin>>text;
		try{
			nowy = stoi(text);
			switch(nowy){

                    case 1:
                        cout<<"You picked INTEGER type for your tree !\n";
                        tree_picked = true;
                        tree_type = "int";
                        break;

                    case 2:
                        cout<<"You picked DOUBLE type for your tree !\n";
                        tree_picked = true;
                        tree_type = "double";
                        break;

                    case 3:
                        cout<<"You picked STRING type for your tree !\n";
                        tree_picked = true;
                        tree_type = "string";
                        break;

                    default:
                        cout<<"Please use only the types that were mentioned earlier !\n";
			break;
                	}
		}
		catch(...){
			cout<<"Please use only the types that were mentioned earlier !\n";
		}
               
            }

            /**
             * WYSYLANIE DO SOCKETA
             */
            cout<<"You can type following commands:\n";
            cout<<"1: '1' and then the VALUE in new line - adds new value to the tree ...\n";
            cout<<"2: '2' and then the VALUE in new line - deletes value from the tree ...\n";
            cout<<"3: '3' - displays actual tree ...\n";
            cout<<"4: '4' and then the VALUE in new line - searches for value in the tree ...\n";
            cout<<"5: '5' - closes the programm for client ...\n";

            /**
             * PETLA DZIALAJACA DOPOKI UZYTKOWNIK TEGO CHCE
             */
            while(!close){

                /**
                 * SWITCH OBSLUGUJACY TEKST WPISYWANY PRZEZ UZYTKOWNIKA
                 */
                cin>>text;
		try{
			nowy = stoi(text);
			switch(nowy){

                    case 1:
                        cout<<"Type a variable of "+tree_type+" type now !\n";
                        if(tree_type == "int") {
				try{
					cin>>text;
					a = stoi(text);
                            integerBinaryTree.insert(a);
cout<<"You added a value !";
				}
                            catch(...){
				cout<<"Please use correct commands !\n";
				}
                        }
                        else if(tree_type=="double") {
                            try{
					cin>>text;
					b = stod(text);
                            doubleBinaryTree.insert(b);
cout<<"You added a value !";
				}
                            catch(...){
				cout<<"Please use correct commands !\n";
				}
                        }
                        else if(tree_type=="string") {
                            try{
					cin>>c;
                            stringBinaryTree.insert(c);
cout<<"You added a value !";
				}
                            catch(...){
				cout<<"Please use correct commands !\n";
				}
                        }
              
                        break;

                    case 2:
                        cout<<"Type a variable of "+tree_type+" type now !\n";
                        if(tree_type=="int") {
                            try{
					cin>>text;
					a = stoi(text);
                            integerBinaryTree.remove(a);
cout<<"You deleted a value !";
				}
                            catch(...){
				cout<<"Please use correct commands !\n";
				}
                        }
                        else if(tree_type=="double") {
                            try{
					cin>>text;
					b = stod(text);
                            doubleBinaryTree.remove(b);
cout<<"You deleted a value !";
				}
                            catch(...){
				cout<<"Please use correct commands !\n";
				}
                        }
                        else if(tree_type=="string") {
                            try{
					cin>>c;
                            stringBinaryTree.remove(c);
cout<<"You deleted a value !";
				}
                            catch(...){
				cout<<"Please use correct commands !\n";
				}
                        }
                       
                        break;

                    case 3:
                        if(tree_type=="int") integerBinaryTree.printInOrder(integerBinaryTree.getRoot());
                        else if(tree_type=="double") doubleBinaryTree.printInOrder(doubleBinaryTree.getRoot());
                        else if(tree_type=="string") stringBinaryTree.printInOrder(stringBinaryTree.getRoot());
                        break;

                    case 4:
                        cout<<"Type a variable of "+tree_type+" type now !\n";
                        if(tree_type=="int"){
				try{
                            cin>>text;
				a = stoi(text);
                            if(integerBinaryTree.search(a)) cout<<"It is true there is an element like that !\n";
                            else cout<<"It is true there is an element like that !\n";
				}
				catch(...){
					cout<<"Please use correct commands !\n";
				}
                        } 
                        else if(tree_type=="double"){
				try{
                            cin>>text;
				b = stod(text);
                            if(doubleBinaryTree.search(b)) cout<<"It is true there is an element like that !\n";
                            else cout<<"It is true there is an element like that !\n";
				}
				catch(...){
					cout<<"Please use correct commands !\n";
				}
                        } 
                        else if(tree_type=="string"){
				try{
                            cin>>c;
                            if(stringBinaryTree.search(c)) cout<<"It is true there is an element like that !\n";
                            else cout<<"It is true there is an element like that !\n";
				}
				catch(...){
					cout<<"Please use correct commands !\n";
				}
                        } 
                        break;

                    case 5:
                        close = true;
                        cout<<"I end my work, see you !\n";
                        break;

                    default:
                        cout<<"Please use correct commands !\n";
			break;
                }
		}
		catch(...){
			cout<<"Please use correct commands !\n";
		}
            }
    
    return 0;
}
