#include<iostream>
template<typename T>

/**
 * @brief KLASA NODE
 * 
 */
class Node
{
    /**
     * @brief DEKLARACJA ZMIENNYCH
     * 
     */
    private:
        T value;
    public:
        Node<T> *left_child = nullptr, *right_child = nullptr, *parent = nullptr;

    /**
     * @brief Construct a new Node object
     * 
     * @param key 
     */
    Node(T value)
    {
        this->value = value;
    }

    /**
     * @brief Get the Value object
     * 
     * @return T 
     */
    T getValue()
    {
        return this->value;
    }

    /**
     * @brief Set the Key object
     * 
     * @param key 
     */
    void setValue(T value)
    {
        this->value = value;
    }
};
