#include <iostream>
#include "Node.hpp"

template<typename T>

/**
 * @brief KLASA BINARYTREE
 * 
 */
class BinaryTree
{
    /**
     * @brief DEKLARACJA ZMIENNYCH
     * 
     */
    private:
        Node<T>* root = nullptr;

    /**
     * @brief PUBLICZNE FUNKCJE KLASY BINARYTREE
     * 
     */
    public:
    Node<T>* getRoot()
    {
	    return this->root;
    }

    Node<T>* search(T value)
    {
	    return search(this->root, value);
    }

    Node<T>* search(Node<T> *firstNode, T value)
    {
        Node<T>* parent = firstNode;
        while(parent != nullptr)
        {
            if(value == parent->getValue())
            {
                return parent;
            }

            if(value < parent->getValue())
            {
                parent = parent->left_child;
            }
            else
            {
                parent = parent->right_child;
            }
        }
        return nullptr;
    }

    void insert(T value)
    {
        if(this->root == nullptr)
        {
            this->root = new Node<T>(value);
            return;
        }

        Node<T>* temp = nullptr;
        Node<T>* root = this->root;

        while(root)
        {
            temp = root;
            if(value < root->getValue()) root = root->left_child;
            else root = root->right_child;
        }

        Node<T>* newNode = new Node<T>(value);
        newNode->parent = temp;
        if(value >= temp->getValue())
        {
            temp->right_child = newNode;
        }
        else temp->left_child = newNode;
    }

    Node<T>* successor(Node<T>* newNode)
    {
        if(newNode->right_child != nullptr)
            return minimumOfSubtree(newNode->right_child);
        Node<T>* anotherNewNode = newNode->parent;
        while(anotherNewNode != nullptr && newNode->right_child == anotherNewNode->right_child)
        {
            newNode = anotherNewNode;
            anotherNewNode = anotherNewNode->parent;
        }
        return anotherNewNode;
    }

    Node<T>* minimumOfSubtree(Node<T>* x)
    {
        while(x->left_child != nullptr)
        {
            x = x->left_child;
        }
        return x;
    }

    void remove(T value)
    {
        remove(this->root, value);
    }

    void remove(Node<T>* rootNode, T value)
    {
        if(rootNode == NULL) return;

        Node<T>* targetNode = search(value);
        Node<T>* x = targetNode;
        Node<T>* y = NULL;

        if(targetNode->left_child == NULL || targetNode->right_child == NULL)
        {
            y = targetNode;
        }
        else
        {
            y = successor(targetNode);
        }
        if(y->left_child != nullptr)
            x = y->left_child;
        else
            x = y->right_child;
        if(x != nullptr)
            x->parent = y->parent;
        if(y->parent == nullptr)
            root = x;
        else if(y == y->parent->left_child)
            y->parent->left_child = x;
        else
            y->parent->right_child = x;
        if(y != targetNode)
            targetNode->setValue(y->getValue());
    }

    void printInOrder(Node<T>* root)
    {
        if(root != NULL)
        {
		printInOrder(root->left_child);
		std::cout << root->getValue() << " ";
		printInOrder(root->right_child);
        }
    }
};
